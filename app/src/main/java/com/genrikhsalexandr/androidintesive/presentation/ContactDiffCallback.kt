package com.genrikhsalexandr.androidintesive.presentation

import androidx.recyclerview.widget.DiffUtil
import com.genrikhsalexandr.androidintesive.domain.contact.ContactItem

class ContactDiffCallback : DiffUtil.ItemCallback<ContactItem>() {

    override fun areItemsTheSame(oldItem: ContactItem, newItem: ContactItem): Boolean {
        return oldItem.id ==newItem.id
    }

    override fun areContentsTheSame(oldItem: ContactItem, newItem: ContactItem): Boolean {
        return oldItem==newItem    }

    override fun getChangePayload(oldItem: ContactItem, newItem: ContactItem): Any? {
        return newItem
    }
}