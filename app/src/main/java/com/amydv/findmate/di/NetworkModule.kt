package com.amydv.findmate.di

import android.content.Context
import com.amydv.findmate.BuildConfig
import com.amydv.findmate.utils.ConnectionStateMonitor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val TIME_OUT = 120L

private const val HEADER_CACHE_CONTROL = "Cache-Control"
private const val HEADER_PRAGMA = "Pragma"

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideConnectionStateMonitor(@ApplicationContext context: Context): ConnectionStateMonitor {
        return ConnectionStateMonitor(context)
    }

    @Provides
    fun makeNetworkInterceptor(): NetworkInterceptor {
        return NetworkInterceptor()
    }

    @Provides
    fun makeOfflineInterceptor(connectionStateMonitor: ConnectionStateMonitor) : OfflineInterceptor {
        return OfflineInterceptor(connectionStateMonitor)
    }

    @Singleton
    @Provides
    fun getOkHttpCache(@ApplicationContext context: Context): Cache {
        val size = (10 * 1024 * 1024).toLong() // 10 Mb
        return Cache(File(context.cacheDir, "OfflineCache"), size)
    }

    @Singleton
    @Provides
    fun makeHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
    }

    @Provides
    fun makeOkHttpClient(
        cache: Cache,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        networkInterceptor: NetworkInterceptor,
        offlineInterceptor: OfflineInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(offlineInterceptor)
        .addNetworkInterceptor(networkInterceptor)
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .cache(cache)
        .build()

    @Provides
    fun provideRetrofitService(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * This interceptor will be called ONLY if the network is available
     */
    class NetworkInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val response = chain.proceed(chain.request())

            val cacheControl = CacheControl.Builder()
                .maxAge(5, TimeUnit.SECONDS)
                .build()

            return response.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                .build()
        }
    }

    /**
     * This interceptor will be called both if the network is available and if the network is not available
     */
    class OfflineInterceptor(private val connectionStateMonitor: ConnectionStateMonitor) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            // prevent caching when network is on. For that we use the "networkInterceptor"
            if (!connectionStateMonitor.hasNetworkConnection()) {
                val cacheControl = CacheControl.Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .build()
                request = request.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .cacheControl(cacheControl)
                    .build()
            }
            return chain.proceed(request)
        }

    }
}