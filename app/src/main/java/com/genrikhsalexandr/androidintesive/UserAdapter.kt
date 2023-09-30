package com.genrikhsalexandr.androidintesive

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.genrikhsalexandr.androidintesive.databinding.UserListItemBinding

class UserAdapter(
    var onContactItemClickListener: ((UserItem) -> Unit)
) : ListAdapter<UserItem, UserAdapter.UserItemViewHolder>(UserDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        return UserItemViewHolder(
            UserListItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class UserItemViewHolder(private val binding: UserListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userItem: UserItem) {
            with(binding) {
                nameContact.text = userItem.name
                surNameContact.text = userItem.surName
                numberContact.text = userItem.number
                iconContact.setImageResource(userItem.image)
                root.setOnClickListener {
                    onContactItemClickListener.invoke(userItem)

                }
            }
        }
    }
}
