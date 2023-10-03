package com.genrikhsalexandr.androidintesive.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.genrikhsalexandr.androidintesive.databinding.ContactListItemBinding
import com.genrikhsalexandr.androidintesive.domain.Contact
import com.genrikhsalexandr.androidintesive.domain.ContactItemList

class ContactAdapter(
    var onContactItemClickListener: ((Contact) -> Unit)
) : ListAdapter<ContactItemList, ContactAdapter.ContactViewHolder>(ContactDiffUtil()) {

    override fun getItemViewType(position: Int): Int {
        return getItem(position).contact.viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            ContactListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ContactViewHolder(private val binding: ContactListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contactItem: ContactItemList) {
            with(binding) {
                nameContact.text = contactItem.name
                surNameContact.text = contactItem.surName
                root.setOnClickListener {
                    onContactItemClickListener(contactItem.contact)

                }
            }
        }
    }
}