package me.drewm.usergeneratorchallenge.network

import me.drewm.usergeneratorchallenge.model.UserDataResults
import retrofit2.Response
import retrofit2.http.GET

interface UserDataService {

    //UPDATE
    @GET("?")
    suspend fun getUserListData(
    ): Response<UserDataResults>
}
