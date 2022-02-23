package com.example.jornadasorientate.data.ui.users

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.jornadasorientate.R
import com.example.jornadasorientate.data.data.model.User
import com.example.jornadasorientate.data.presenter.UsersPresenter
import com.example.jornadasorientate.data.ui.detail.DetailActivity
import com.example.jornadasorientate.data.ui.detail.DetailActivity.Companion.USER_EXTRA
import com.example.jornadasorientate.databinding.ActivityUsersBinding

class UsersActivity : AppCompatActivity(), UsersPresenter.PhotosView {

    private val presenter = UsersPresenter(this)

    private lateinit var binding: ActivityUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter.getUsers()
    }

    override fun showLoading(show: Boolean) {
        binding.progress.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showUsers(users: List<User>) {
        UsersAdapter(this, users) { user ->
            showUserDetail(user)
        }.also {
            binding.recyclerView.adapter = it
        }
    }

    override fun showError() {
        binding.recyclerView.visibility = View.GONE
        binding.errorTextView.visibility = View.VISIBLE
        binding.errorTextView.text = getString(R.string.generic_error)
    }

    private fun showUserDetail(user: User) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(USER_EXTRA, user)
        startActivity(intent)
    }
}
