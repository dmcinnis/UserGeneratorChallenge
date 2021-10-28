package me.drewm.usergeneratorchallenge

import me.drewm.usergeneratorchallenge.network.UserDataService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This is a manual DI implementation / container for app singletons
  */
class AppContainer {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://randomuser.me/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val userApiService: UserDataService by lazy {
        retrofit.create(UserDataService::class.java)
    }
}
