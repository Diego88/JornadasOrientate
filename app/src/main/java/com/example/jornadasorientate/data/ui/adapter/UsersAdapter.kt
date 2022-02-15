package com.example.jornadasorientate.data.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jornadasorientate.R
import com.example.jornadasorientate.data.model.User
import com.example.jornadasorientate.data.util.loadImage

class UsersAdapter: RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    var items: List<User> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = items[position].name
        holder.email.text = items[position].email
        holder.avatar.loadImage(items[position].avatar)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.nameTextView)
        val email: TextView = itemView.findViewById(R.id.emailTextView)
        val avatar: ImageView = itemView.findViewById(R.id.avatarImageView)
    }
}
