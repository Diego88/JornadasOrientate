package com.example.jornadasorientate.data.ui.users

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.example.jornadasorientate.R
import com.example.jornadasorientate.data.data.model.User
import com.example.jornadasorientate.data.util.loadImage
import com.example.jornadasorientate.databinding.ListItemBinding

class UsersAdapter(
    private val context: Context,
    private val items: List<User>,
    private val listener: (User) -> Unit
): RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val itemBinding: ListItemBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: User) {
            itemBinding.nameTextView.text = context.getString(R.string.complete_user_name, item.firstName, item.lastName)
            itemBinding.emailTextView.text = item.email
            itemBinding.avatarImageView.loadImage(context, item.avatar, RequestOptions.circleCropTransform())

            itemBinding.root.setOnClickListener { listener(item) }
        }
    }
}
