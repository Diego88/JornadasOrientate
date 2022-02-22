package com.example.jornadasorientate.data.data.model

import com.google.gson.annotations.SerializedName

data class UsersResult(
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("data")
    val data: List<User>
)
