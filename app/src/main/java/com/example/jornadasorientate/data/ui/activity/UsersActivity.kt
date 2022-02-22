package com.example.jornadasorientate.data.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.jornadasorientate.R
import com.example.jornadasorientate.data.data.model.User
import com.example.jornadasorientate.data.presenter.UsersPresenter
import com.example.jornadasorientate.data.ui.adapter.UsersAdapter

class UsersActivity : AppCompatActivity(), UsersPresenter.PhotosView {

    private val presenter = UsersPresenter(this)

    private val recyclerView: RecyclerView by lazy { findViewById(R.id.recyclerView) }

    private val progress: ProgressBar by lazy { findViewById(R.id.progress) }

    private val error: TextView by lazy { findViewById(R.id.errorTextView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.getUsers()
    }

    override fun showLoading(show: Boolean) {
        progress.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showUsers(users: List<User>) {
        UsersAdapter(this, users) { user ->
            showUserDetail(user)
        }.also {
            recyclerView.adapter = it
        }
    }

    override fun showError(message: String) {
        recyclerView.visibility = View.GONE
        error.visibility = View.VISIBLE
        error.text = message
    }

    private fun showUserDetail(user: User) {
        Toast.makeText(this, getString(R.string.complete_user_name, user.firstName, user.lastName), Toast.LENGTH_SHORT)
            .show()
    }
}
