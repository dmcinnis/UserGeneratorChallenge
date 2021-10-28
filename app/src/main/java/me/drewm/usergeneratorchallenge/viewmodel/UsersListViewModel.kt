package me.drewm.usergeneratorchallenge.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.receiveAsFlow
import me.drewm.usergeneratorchallenge.model.User
import me.drewm.usergeneratorchallenge.repo.UserDataRepository

class UsersListViewModel(private val userDataRepository: UserDataRepository) : ViewModel() {

    private val eventsChannel = Channel<Event>(Channel.BUFFERED)
    val eventsFlow = eventsChannel.receiveAsFlow()

    private val mutableUsersDataFlow = MutableStateFlow(emptyList<User>())
    val usersDataFlow = mutableUsersDataFlow.asStateFlow()

    init {
        viewModelScope.launch {
            //show spinner (shout event)
            val userList = userDataRepository.getUserListData(25)
            //hide spinner (shout event)
            mutableUsersDataFlow.value = userList
            Log.d("asdf", userList.toString())
        }
    }

    // could change param to position, convert to user from userList data here
    fun onUserClicked(user: User) {
        viewModelScope.launch {
            eventsChannel.send(Event.NavigateToUserDetails(user))
        }
    }
}

sealed class Event {
    object ShowSpinner : Event()
    object HideSpinner : Event()
    // need a way to pass User or something
    data class NavigateToUserDetails(val user: User) : Event()
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
