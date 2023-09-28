package com.genrikhsalexandr.androidintesive.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.genrikhsalexandr.androidintesive.R
import com.genrikhsalexandr.androidintesive.databinding.ListItemContactsEnabledBinding
import com.genrikhsalexandr.androidintesive.domain.contact.ContactItem

class ContactsAdapter :
    ListAdapter<ContactItem, ContactsAdapter.ContactItemViewHolder>(ContactDiffCallback()) {


    var contactsList = listOf<ContactItem>()

    var onContactItemClickListener: ((ContactItem) -> Unit)? = null


    fun submitData(list: List<ContactItem>) {
        this.contactsList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactItemViewHolder {
            val layout = when (viewType) {
            VIEW_TYPE_DISABLED -> R.layout.list_item_contacts_disabled
            VIEW_TYPE_ENABLED -> R.layout.list_item_contacts_enabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        return ContactItemViewHolder(
            ListItemContactsEnabledBinding.inflate(
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

    override fun getItemViewType(position: Int): Int {
        val item = contactsList[position]
        return if (item.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    inner class ContactItemViewHolder(private val binding: ListItemContactsEnabledBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contactItem: ContactItem) {
            with(binding) {
                nameContact.text = contactItem.name
                surNameContact.text =contactItem.surName
                numberContact.text =contactItem.number
                root.setOnClickListener {
                    onContactItemClickListener?.invoke(contactItem)

                }
            }
        }
    }

    companion object {

        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101
    }
}