package com.genrikhsalexandr.androidintesive.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.genrikhsalexandr.androidintesive.R
import com.genrikhsalexandr.androidintesive.databinding.ListItemContactsFullBinding
import com.genrikhsalexandr.androidintesive.databinding.ListItemContactsShortBinding
import com.genrikhsalexandr.androidintesive.domain.contact.ContactItem


class ContactsAdapter(
    var onContactItemClickListener: ((ContactItem) -> Unit)
) : ListAdapter<ContactItem, RecyclerView.ViewHolder>(ContactDiffCallback()) {

    var count = 0
    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("xxx", "onCreateViewHolder ${++count}")
        return when (viewType) {
            R.layout.list_item_contacts_short -> {
                ContactItemShortViewHolder(
                    ListItemContactsShortBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            R.layout.list_item_contacts_full -> {
                ContactItemFullViewHolder(
                    ListItemContactsFullBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            else -> error("Unknown view type: $viewType")

        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        Log.d("xxx", "onBindViewHolder ${++count}")
        when (holder) {
            is ContactItemShortViewHolder -> {
                holder.bindShort(getItem(position))
            }
            is ContactItemFullViewHolder -> {
                holder.bindFull((getItem(position)))
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        when (holder) {
            is ContactItemShortViewHolder -> {
                if (payloads.isEmpty()) {
                    onBindViewHolder(holder, position)
                    return
                }
                val updatedItem: ContactItem = payloads.first() as ContactItem
                holder.update(updatedItem)

            }

            is ContactItemFullViewHolder -> {
                if (payloads.isEmpty()) {
                    onBindViewHolder(holder, position)
                    return
                }
                val updatedItem: ContactItem = payloads.first() as ContactItem
                holder.update(updatedItem)
            }
        }
        Log.d("xxx", "payload ${++count}")

    }

    inner class ContactItemShortViewHolder(private val binding: ListItemContactsShortBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindShort(contactItem: ContactItem) {
            with(binding) {
                nameContact.text = contactItem.name
                surNameContact.text = contactItem.surName
                numberContact.text = contactItem.number
                root.setOnClickListener {
                    onContactItemClickListener.invoke(contactItem)
                    update(contactItem)
                }
            }
        }

        fun update(updatedItem: ContactItem) {
            binding.root.isSelected = updatedItem.isSelected
            if (updatedItem.isSelected) {
                binding.root.setBackgroundColor(itemView.context.getColor(R.color.blue))

            } else {
                binding.root.setBackgroundColor(itemView.context.getColor(R.color.white))
            }
            itemView.setOnClickListener {
                onContactItemClickListener.invoke(updatedItem)
                updatedItem.isSelected =
                    !updatedItem.isSelected
                update(updatedItem)
            }
        }
    }

    inner class ContactItemFullViewHolder(private val binding: ListItemContactsFullBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindFull(contactItem: ContactItem) {
            contactItem as ContactItem.Full
            with(binding) {
                nameContact.text = contactItem.name
                surNameContact.text = contactItem.surName
                numberContact.text = contactItem.number
                birthday.text = contactItem.birthDay
                root.setOnClickListener {
                    onContactItemClickListener.invoke(contactItem)
                    update(contactItem)
                }
            }
        }

        fun update(updatedItem: ContactItem) {
            binding.root.isSelected = updatedItem.isSelected
            if (updatedItem.isSelected) {
                binding.root.setBackgroundColor(itemView.context.getColor(R.color.blue))

            } else {
                binding.root.setBackgroundColor(itemView.context.getColor(R.color.white))
            }
            itemView.setOnClickListener {
                onContactItemClickListener.invoke(updatedItem)
                updatedItem.isSelected = !updatedItem.isSelected
                update(updatedItem)
            }
        }
    }
}