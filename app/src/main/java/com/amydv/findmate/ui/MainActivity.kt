package com.amydv.findmate.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.amydv.domain.models.UserUiModel
import com.amydv.findmate.R
import com.amydv.findmate.databinding.ActivityMainBinding
import com.amydv.findmate.ui.adapter.UserAdapter
import com.amydv.findmate.utils.setVisible
import com.amydv.presentation.states.Resource
import com.amydv.presentation.viewmodels.GetRandomUserViewModel
import com.amydv.presentation.states.ResourceState
import com.amydv.presentation.viewmodels.UpdateUserStatusViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val randomUserViewModel by viewModels<GetRandomUserViewModel>()
    private val updateUserStatusViewModel by viewModels<UpdateUserStatusViewModel>()

    private lateinit var mAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        randomUserViewModel.loadRandomUser(10)

        setupObserver()
        setupRecycler()
    }

    private fun setupObserver() {
        randomUserViewModel.users.observe(this, getRansomUserObserver())
    }

    private fun getRansomUserObserver(): Observer<Resource<List<UserUiModel>>?> {
        return Observer {
            val resource = it ?: return@Observer
            when (resource.status) {
                ResourceState.LOADING -> {
                    binding.progress.setVisible(true)
                }
                ResourceState.SUCCESS -> {
                    updateList(resource.data)
                    binding.progress.setVisible(false)
                }
                ResourceState.ERROR -> {
                    binding.error.setVisible(mAdapter.currentList.isEmpty())
                    binding.progress.setVisible(false)
                }
            }
        }
    }

    private fun setupRecycler() {
        mAdapter = UserAdapter { userUiModel ->
            updateUserStatusViewModel.update(userUiModel)
        }
        binding.rvUsers.apply {
            adapter = mAdapter
            setHasFixedSize(true)
        }
    }

    private fun updateList(users: List<UserUiModel>?) {
        val list = users?.reversed() ?: emptyList()
        mAdapter.submitList(list)
        binding.rvUsers.postDelayed({
            if (list.isNotEmpty()) binding.rvUsers.smoothScrollToPosition(0)
            binding.error.setVisible(mAdapter.currentList.isEmpty())
        }, 100)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_load_more) {
            randomUserViewModel.loadRandomUser(10)
        }
        return super.onOptionsItemSelected(item)
    }
}