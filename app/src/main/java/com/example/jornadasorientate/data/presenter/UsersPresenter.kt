package com.example.jornadasorientate.data.presenter

import com.example.jornadasorientate.data.data.api.ApiService
import com.example.jornadasorientate.data.data.model.DataUsers
import com.example.jornadasorientate.data.data.model.mapToUsers
import com.example.jornadasorientate.data.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersPresenter(private val view: PhotosView) {

    fun getPhotos() {
        view.showLoading(true)

        val apiService = ApiService.create()
        val call = apiService.getUsers()
        call.enqueue(object : Callback<DataUsers> {
            override fun onResponse(call: Call<DataUsers>, response: Response<DataUsers>) {
                view.showLoading(false)
                response.body()?.let { dataList ->
                    view.showPhotos(dataList.data.mapToUsers())
                }
            }

            override fun onFailure(call: Call<DataUsers>, t: Throwable) {
                val message = t.message ?: "Ha ocurrido un error!"
                view.showLoading(false)
                view.showError(message)
            }
        })
    }

    interface PhotosView {
        fun showLoading(show: Boolean)
        fun showPhotos(users: List<User>)
        fun showError(message: String)
    }
}
