package com.example.jornadasorientate.data.ui.detail

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.jornadasorientate.R
import com.example.jornadasorientate.data.data.model.User
import com.example.jornadasorientate.data.util.loadImage

class DetailActivity : AppCompatActivity() {

    private val avatarDetailImageView: ImageView by lazy {
        findViewById(R.id.avatarDetailImageView)
    }

    private val nameDetailTextView: TextView by lazy {
        findViewById(R.id.nameDetailTextView)
    }

    private val emailDetailTextView: TextView by lazy {
        findViewById(R.id.emailDetailTextView)
    }

    companion object {
        const val USER_EXTRA = "USER_EXTRA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        intent.getParcelableExtra<User>(USER_EXTRA)?.let { user ->
            avatarDetailImageView.loadImage(this, user.avatar)
            nameDetailTextView.text = getString(
                R.string.user_name_label,
                getString(R.string.complete_user_name, user.firstName, user.lastName)
            )
            emailDetailTextView.text = getString(R.string.email_label, user.email)
        }
    }
}
