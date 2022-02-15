package com.example.jornadasorientate.data.data.model

import com.example.jornadasorientate.data.model.User
import com.google.gson.annotations.SerializedName

data class DataUsers(
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("data")
    val data: List<DataUser>
)

fun List<DataUser>.mapToUsers() = this.map { it.mapToUser() }

fun DataUser.mapToUser() =
    User(
        id,
        "$firstName $lastName",
        email,
        avatar
    )
