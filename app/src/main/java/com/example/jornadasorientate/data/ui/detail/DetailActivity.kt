package com.example.jornadasorientate.data.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jornadasorientate.R
import com.example.jornadasorientate.data.data.model.User
import com.example.jornadasorientate.data.util.loadImage
import com.example.jornadasorientate.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val USER_EXTRA = "USER_EXTRA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getParcelableExtra<User>(USER_EXTRA)?.let { user ->
            binding.avatarDetailImageView.loadImage(this, user.avatar)
            binding.nameDetailTextView.text = getString(
                R.string.user_name_label,
                getString(R.string.complete_user_name, user.firstName, user.lastName)
            )
            binding.emailDetailTextView.text = getString(R.string.email_label, user.email)
        }
    }
}
