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

    private lateinit var users: MutableLiveData<List<SelectableItem<User>>>

    private lateinit var items: MutableLiveData<List<SelectableItem<PollItem>>>

    private val _mode: MutableLiveData<Mode> = MutableLiveData()

    fun getUsers(): LiveData<List<SelectableItem<User>>> {
        if (!::users.isInitialized) {
            users = MutableLiveData()
            uiScope.launch {
                users.value = loadUsers()
            }
        }

        return users
    }

    fun getItems(): LiveData<List<SelectableItem<PollItem>>> {
        if (!::items.isInitialized) {
            items = MutableLiveData()
            uiScope.launch {
                items.value = loadItems()
            }
        }

        return items
    }

    private suspend fun loadUsers(): List<SelectableItem<User>> {
        return coroutineScope {
            val candidates = async {
                listOf("A", "B", "C", "D")
                    .map { User(Id(it), it) }
                    .map { SelectableItem(it, false) }
            }

            candidates.await()
        }
    }

    private suspend fun loadItems(): List<SelectableItem<PollItem>> {
        return coroutineScope {
            val candidates = async {
                listOf("Whiskey", "Vodka", "Long Island", "Kahlua")
                    .map { PollItem(Id(it), it) }
                    .map { SelectableItem(it, false) }
            }

            candidates.await()
        }
    }

    fun getMode(): LiveData<Mode> {
        return _mode
    }

    fun changeMode(mode: Mode) {
        _mode.value = mode
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}