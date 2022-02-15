package com.example.jornadasorientate.data.data.model

import com.google.gson.annotations.SerializedName

data class DataUser(
    @SerializedName("id")
    val id: Int,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("avatar")
    val avatar: String
)
