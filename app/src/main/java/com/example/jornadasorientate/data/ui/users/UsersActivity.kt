package com.example.jornadasorientate.data.ui.users

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.jornadasorientate.R
import com.example.jornadasorientate.data.data.model.User
import com.example.jornadasorientate.data.presenter.UsersPresenter
import com.example.jornadasorientate.data.ui.detail.DetailActivity
import com.example.jornadasorientate.data.ui.detail.DetailActivity.Companion.USER_EXTRA

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
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(USER_EXTRA, user)
        startActivity(intent)
    }
}
