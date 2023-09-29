package com.genrikhsalexandr.androidintesive.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.genrikhsalexandr.androidintesive.data.contact.ContactRepositoryImpl
import com.genrikhsalexandr.androidintesive.databinding.FragmentContactsBinding
import com.genrikhsalexandr.androidintesive.domain.contact.ContactItem
import com.genrikhsalexandr.androidintesive.domain.contact.DeleteContactItemUseCase
import com.genrikhsalexandr.androidintesive.domain.contact.EditContactItemUseCase
import com.genrikhsalexandr.androidintesive.domain.contact.GetContactsListUseCase


class ContactsFragment : Fragment() {

    private var _binding: FragmentContactsBinding? = null
    private val binding: FragmentContactsBinding get() = _binding!!

    private val contactRepository = ContactRepositoryImpl

    private val getContactsListUseCase = GetContactsListUseCase(contactRepository)

    private val deleteContactItemUseCase = DeleteContactItemUseCase(contactRepository)

    private val editContactItemUseCase = EditContactItemUseCase(contactRepository)

    private val contactAdapter: ContactsAdapter = ContactsAdapter(
        onContactItemClickListener = { clickedContactItem ->

        },
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        binding.listItemContacts.adapter = contactAdapter
        contactAdapter.submitList(getContactItem())
        contactAdapter.onContactItemClickListener
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSwipeListener(binding.listItemContacts)
        setupClickListener()
    }

    private fun getContactItem(): List<ContactItem> {
        return getContactsListUseCase.getContactsList()
    }

    private fun delContactItem(contactItem: ContactItem) {
        deleteContactItemUseCase.deleteContactItem(contactItem)
    }

    private fun changeContactItem(contactItem: ContactItem) {
        editContactItemUseCase.editContactItem(contactItem)
    }


    private fun setupSwipeListener(rvContactsList: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = contactAdapter.currentList[viewHolder.adapterPosition]
                delContactItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvContactsList)
    }

    private fun setupClickListener() {
        contactAdapter.onContactItemClickListener = {
            changeIsSelectedState(it)
        }
    }

    fun changeIsSelectedState(contactItem: ContactItem) {

        editContactItemUseCase.editContactItem(contactItem)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}