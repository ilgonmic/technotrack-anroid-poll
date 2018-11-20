package com.ilgonmic.poll.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ilgonmic.poll.data.PollItem
import com.ilgonmic.poll.data.User

class DistributorViewModel : ViewModel() {

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

    }

    private fun loadItems() {
        // Do an asynchronous operation to fetch users.
    }
}