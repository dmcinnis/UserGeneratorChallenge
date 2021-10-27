package me.drewm.usergeneratorchallenge.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.drewm.usergeneratorchallenge.model.User
import me.drewm.usergeneratorchallenge.model.UserDataResults
import me.drewm.usergeneratorchallenge.network.UserDataService
import retrofit2.Response

// maybe this class is redundant, should just put this in VM for now
class UserDataRepository(private val userDataService: UserDataService) {

//    suspend fun getUserListData(): Response<List<User>> {
//        return withContext(Dispatchers.IO) {
//            when (val response = userDataService.getUserListData()) {
//                    // maybe conversion in another place?
//                is Response.Success -> Response.Success(response.data.results)
//                is Response.Failure -> Response.Failure(response.exception)
//            }
//        }
//    }

    suspend fun getUserListData(): List<User> {
        return withContext(Dispatchers.IO) {
            val response = userDataService.getUserListData()
            when {
                response.isSuccessful -> (response.body() as UserDataResults).results
                // will wanna update so we can throw an error instead...maybe use a custom
                // Response-esque sealed class (dif name for disambiguation)
                else -> emptyList()
            }
        }
    }
}
