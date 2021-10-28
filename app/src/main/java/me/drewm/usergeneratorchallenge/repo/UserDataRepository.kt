package me.drewm.usergeneratorchallenge.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.drewm.usergeneratorchallenge.model.User
import me.drewm.usergeneratorchallenge.model.UserDataResults
import me.drewm.usergeneratorchallenge.network.UserDataService

class UserDataRepository(private val userDataService: UserDataService) {

    suspend fun getUserListData(numUsers: Int): List<User> {
        return withContext(Dispatchers.IO) {
            val response = userDataService.getUserListData(numUsers)
            when {
                response.isSuccessful -> (response.body() as UserDataResults).results
                // TODO improved error-handling
                else -> emptyList()
            }
        }
    }
}
