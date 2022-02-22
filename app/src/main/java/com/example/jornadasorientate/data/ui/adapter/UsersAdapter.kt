package com.example.jornadasorientate.data.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jornadasorientate.R
import com.example.jornadasorientate.data.data.model.User
import com.example.jornadasorientate.data.util.loadImage

class UsersAdapter(private val context: Context): RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    var items: List<User> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.nameTextView)
        private val email: TextView = itemView.findViewById(R.id.emailTextView)
        private val avatar: ImageView = itemView.findViewById(R.id.avatarImageView)

        fun bind(item: User) {
            name.text = context.getString(R.string.complete_user_name, item.firstName, item.lastName)
            email.text = item.email
            avatar.loadImage(item.avatar)
        }
    }
}
