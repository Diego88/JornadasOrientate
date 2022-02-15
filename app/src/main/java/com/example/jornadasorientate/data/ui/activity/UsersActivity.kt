package com.example.jornadasorientate.data.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.jornadasorientate.R
import com.example.jornadasorientate.data.model.User
import com.example.jornadasorientate.data.presenter.UsersPresenter
import com.example.jornadasorientate.data.ui.adapter.UsersAdapter

class UsersActivity : AppCompatActivity(), UsersPresenter.PhotosView {

    private val presenter = UsersPresenter(this)

    private val recyclerView: RecyclerView by lazy { findViewById(R.id.recyclerView) }

    private val progress: ProgressBar by lazy { findViewById(R.id.progress) }

    private val error: TextView by lazy { findViewById(R.id.errorTextView) }

    private val usersAdapter: UsersAdapter by lazy { UsersAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecycler()
        presenter.getPhotos()
    }

    override fun showLoading(show: Boolean) {
        progress.visibility = if (show) View.VISIBLE else View.GONE
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun showPhotos(users: List<User>) {
        with(usersAdapter) {
            items = users
            notifyDataSetChanged()
        }
    }

    override fun showError(message: String) {
        error.visibility = View.VISIBLE
        error.text = message
    }

    private fun setupRecycler() {
        recyclerView.adapter = usersAdapter
    }
}
