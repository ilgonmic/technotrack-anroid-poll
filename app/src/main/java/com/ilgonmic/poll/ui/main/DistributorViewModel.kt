package com.ilgonmic.poll.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ilgonmic.poll.data.Id
import com.ilgonmic.poll.data.PollItem
import com.ilgonmic.poll.data.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DistributorViewModel : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private lateinit var users: MutableLiveData<List<User>>
    private lateinit var items: MutableLiveData<List<PollItem>>

    fun getUsers(): LiveData<List<User>> {
        if (!::users.isInitialized) {
            users = MutableLiveData()
            loadUsers()
        }

        return users
    }

    fun getItems(): LiveData<List<User>> {
        if (!::items.isInitialized) {
            items = MutableLiveData()
            loadItems()
        }

        return users
    }

    private fun loadUsers() {
        uiScope.launch {
            users.value = listOf("A", "B", "C", "D")
                .map { User(Id(it), it) }
        }
    }

    private fun loadItems() {
        // Do an asynchronous operation to fetch users.
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}