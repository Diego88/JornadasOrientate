package com.example.jornadasorientate.data.presenter

import com.example.jornadasorientate.data.data.api.ApiService
import com.example.jornadasorientate.data.data.model.User
import com.example.jornadasorientate.data.data.model.UsersResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersPresenter(private val view: PhotosView) {

    fun getUsers() {
        view.showLoading(true)

        val apiService = ApiService.create()
        val call = apiService.getUsers()
        call.enqueue(object : Callback<UsersResult> {
            override fun onResponse(call: Call<UsersResult>, response: Response<UsersResult>) {
                view.showLoading(false)
                response.body()?.let { users ->
                    view.showUsers(users.data)
                }
            }

            override fun onFailure(call: Call<UsersResult>, t: Throwable) {
                val message = t.message ?: "Something went wrong!"
                view.showLoading(false)
                view.showError(message)
            }
        })
    }

    interface PhotosView {
        fun showLoading(show: Boolean)
        fun showUsers(users: List<User>)
        fun showError(message: String)
    }
}
