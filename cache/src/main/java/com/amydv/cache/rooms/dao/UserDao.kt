package com.amydv.cache.rooms.dao

import androidx.room.*
import com.amydv.cache.rooms.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveUsers(userEntity: List<UserEntity>)

    @Query("SELECT * FROM user")
    suspend fun getUsers() : List<UserEntity>

    @Query("Update user set status = :status where dob = :dob and email = :email")
    suspend fun updateUserStatus(status: Int, dob: String, email: String)

    @Query("DELETE FROM user")
    suspend fun clearUser()

}