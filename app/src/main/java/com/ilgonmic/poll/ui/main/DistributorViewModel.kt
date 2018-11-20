package com.ilgonmic.poll.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ilgonmic.poll.data.Id
import com.ilgonmic.poll.data.PollItem
import com.ilgonmic.poll.data.User
import kotlinx.coroutines.*

class DistributorViewModel : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private lateinit var users: MutableLiveData<List<User>>
    private lateinit var selectedUsers: MutableLiveData<List<User>>

    private lateinit var items: MutableLiveData<List<PollItem>>
    private lateinit var selecteItems: MutableLiveData<List<PollItem>>

    fun getUsers(): LiveData<List<User>> {
        if (!::users.isInitialized) {
            users = MutableLiveData()
            uiScope.launch {
                users.value = loadUsers()
            }
        }

        return users
    }

    fun getSelectedUsers(): LiveData<List<User>> {
        if (!::users.isInitialized) {
            selectedUsers = MutableLiveData()
            selectedUsers.value = emptyList()
        }

        return selectedUsers
    }

    fun getItems(): LiveData<List<PollItem>> {
        if (!::items.isInitialized) {
            items = MutableLiveData()
            uiScope.launch {
                items.value = loadItems()
            }
        }

        return items
    }

    fun getSelectedItems(): LiveData<List<PollItem>> {
        if (!::users.isInitialized) {
            selecteItems = MutableLiveData()
            selecteItems.value = emptyList()
        }

        return selecteItems
    }

    private suspend fun loadUsers(): List<User> {
        return coroutineScope {
            val candidates = async {
                listOf("A", "B", "C", "D")
                    .map { User(Id(it), it) }
            }

            candidates.await()
        }
    }

    private suspend fun loadItems(): List<PollItem> {
        return coroutineScope {
            val candidates = async {
                listOf("Whiskey", "Vodka", "Long Island", "Kahlua")
                    .map { PollItem(Id(it), it) }
            }

            candidates.await()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}