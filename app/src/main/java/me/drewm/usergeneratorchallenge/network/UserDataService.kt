package me.drewm.usergeneratorchallenge.network

import me.drewm.usergeneratorchallenge.model.UserDataResults
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserDataService {

    @GET("?")
    suspend fun getUserListData(
        @Query("results") numUsers: Int
    ): Response<UserDataResults>
}
