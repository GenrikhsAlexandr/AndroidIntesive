package com.genrikhsalexandr.androidintesive.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.genrikhsalexandr.androidintesive.databinding.ListItemContactsBinding
import com.genrikhsalexandr.androidintesive.domain.contact.ContactItem

class ContactsAdapter : RecyclerView.Adapter<ContactsAdapter.ContactItemViewHolder>() {


     var contactsList = listOf<ContactItem>()

    fun submitData(list: List<ContactItem>) {
        this.contactsList = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactItemViewHolder {
        return ContactItemViewHolder(
            ListItemContactsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ContactItemViewHolder, position: Int) {
        holder.bind(contactsList[position])

    }
    override fun getItemCount(): Int {
        return contactsList.size
    }

    inner class ContactItemViewHolder(private val binding: ListItemContactsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contactItem: ContactItem) {
            with(binding) {
                nameContact.text = contactItem.name
                surNameContact.text = contactItem.surName
                numberContact.text = contactItem.number.toString()
                root.setOnClickListener {
                    true
                }
            }
        }
    }
}