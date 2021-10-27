package me.drewm.usergeneratorchallenge.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.drewm.usergeneratorchallenge.repo.UserDataRepository

class UsersListViewModel(private val userDataRepository: UserDataRepository) : ViewModel() {

    init {
        viewModelScope.launch {
            val userList = userDataRepository.getUserListData()
            Log.d("asdf", userList.toString())
        }
    }

    fun doSomething() {
        Log.d("asdf", "did something")
    }
}

class UsersListViewModelFactory(
    private val userDataRepository: UserDataRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UsersListViewModel(
            userDataRepository = userDataRepository
        ) as T
    }
}
