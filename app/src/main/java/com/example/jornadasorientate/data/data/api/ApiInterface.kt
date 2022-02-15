package com.example.jornadasorientate.data.data.api

import com.example.jornadasorientate.data.data.model.DataUsers
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("users?per_page=12")
    fun getUsers(): Call<DataUsers>
}
