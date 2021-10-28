package me.drewm.usergeneratorchallenge.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("name")
    val name: Name,
    @SerializedName("picture")
    val picture: Picture,
    @SerializedName("dob")
    val dob: DateOfBirth,
    @SerializedName("email")
    val email: String = "",
    @SerializedName("cell")
    val cell: String = "",
    @SerializedName("location")
    val location: Location
) : Serializable

data class Name(
    @SerializedName("title")
    val title: String = "",
    @SerializedName("first")
    val first: String = "",
    @SerializedName("last")
    val last: String = ""
)

data class Picture(
    @SerializedName("thumbnail")
    val thumbnail: String = "",
    @SerializedName("large")
    val large: String = ""
)

data class DateOfBirth(
    @SerializedName("date")
    val date: String = "",
    @SerializedName("age")
    val age: String = ""
)

data class Location(
    @SerializedName("timezone")
    val timezone: Timezone
)

data class Timezone(
    @SerializedName("description")
    val description: String = ""
)
