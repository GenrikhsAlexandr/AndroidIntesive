package com.genrikhsalexandr.androidintesive.presentation

import androidx.recyclerview.widget.DiffUtil
import com.genrikhsalexandr.androidintesive.domain.ContactItemList

class ContactDiffUtil : DiffUtil.ItemCallback<ContactItemList>() {

    override fun areItemsTheSame(oldItem: ContactItemList, newItem: ContactItemList): Boolean {
        return oldItem.id ==newItem.id
    }

    override fun areContentsTheSame(oldItem: ContactItemList, newItem: ContactItemList): Boolean {
        return oldItem==newItem    }
}