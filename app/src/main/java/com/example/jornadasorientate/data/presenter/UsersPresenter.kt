package com.example.jornadasorientate.data.presenter

import com.example.jornadasorientate.data.data.api.ApiService
import com.example.jornadasorientate.data.data.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class UsersPresenter(private val view: PhotosView): CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    fun getUsers() {
        view.showLoading(true)

        launch {
            val apiService = ApiService.create()
            val usersResult = withContext(Dispatchers.IO) { apiService.getUsers() }
            if (usersResult.data.isNullOrEmpty()) {
                view.showLoading(false)
                view.showError()
            } else {
                view.showLoading(false)
                view.showUsers(usersResult.data)
            }
        }
    }

    interface PhotosView {
        fun showLoading(show: Boolean)
        fun showUsers(users: List<User>)
        fun showError()
    }
}
