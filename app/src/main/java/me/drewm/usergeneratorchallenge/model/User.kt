package me.drewm.usergeneratorchallenge.model

import com.google.gson.annotations.SerializedName

//deliberately selecting certain data
data class User(
    @SerializedName("name")
    val name: Name,

    // expand to what i wanna use

)

data class Name(
    @SerializedName("title")
    val title: String = "",
    @SerializedName("first")
    val first: String = "",
    @SerializedName("last")
    val last: String = ""
)
