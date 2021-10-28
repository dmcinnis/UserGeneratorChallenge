package me.drewm.usergeneratorchallenge.model

import com.google.gson.annotations.SerializedName

data class UserDataResults(
    @SerializedName("results")
    val results: List<User>
)
