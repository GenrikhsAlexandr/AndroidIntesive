package com.genrikhsalexandr.androidintesive

import androidx.recyclerview.widget.DiffUtil

class UserDiffUtil : DiffUtil.ItemCallback<UserItem>() {

    override fun areItemsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
        return oldItem.id ==newItem.id
    }

    override fun areContentsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
        return oldItem==newItem    }

    override fun getChangePayload(oldItem: UserItem, newItem: UserItem): Any? {
        return newItem
    }
}