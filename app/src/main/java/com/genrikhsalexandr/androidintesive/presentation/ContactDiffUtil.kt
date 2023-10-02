package com.genrikhsalexandr.androidintesive.presentation

import androidx.recyclerview.widget.DiffUtil
import com.genrikhsalexandr.androidintesive.domain.Contact

class ContactDiffUtil : DiffUtil.ItemCallback<Contact>() {

    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem.id ==newItem.id
    }

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem==newItem    }
}