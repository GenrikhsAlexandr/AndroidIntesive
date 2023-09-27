package com.genrikhsalexandr.androidintesive.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.genrikhsalexandr.androidintesive.data.contact.ContactRepositoryImpl
import com.genrikhsalexandr.androidintesive.databinding.FragmentContactsBinding
import com.genrikhsalexandr.androidintesive.domain.contact.ContactItem
import com.genrikhsalexandr.androidintesive.domain.contact.DeleteContactItemUseCase
import com.genrikhsalexandr.androidintesive.domain.contact.EditContactItemUseCase
import com.genrikhsalexandr.androidintesive.domain.contact.GetContactsListUseCase


class ContactsFragment : Fragment() {

    private var _binding: FragmentContactsBinding? = null
    private val binding: FragmentContactsBinding get() = _binding!!

    private val viewModel: ContactsViewModel by viewModels()

    private val contactRepository = ContactRepositoryImpl

    private val getContactsListUseCase = GetContactsListUseCase(contactRepository)

    private val deleteContactItemUseCase = DeleteContactItemUseCase(contactRepository)

    private val editContactItemUseCase = EditContactItemUseCase(contactRepository)

    private  val adapter: ContactsAdapter = ContactsAdapter()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        binding.listItemContacts.adapter = adapter
        val contactsList = generateContacts()
        adapter.submitData(contactsList)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun getContactItem(): List<ContactItem> {
        return getContactsListUseCase.getContactsList()
    }

    private fun delContactItem(contactItem: ContactItem) {
        deleteContactItemUseCase.deleteContactItem(contactItem)
        getContactItem()
    }

    private fun changeContactItem(contactItem: ContactItem) {
        editContactItemUseCase.editContactItem(contactItem)
        getContactItem()
    }

    private fun generateContacts(): List<ContactItem> {
        val contacts = mutableListOf<ContactItem>()

        for (a in 1..100) {
            val number = a + 89990000000
            val item = ContactItem("Name $a", "Surname $a", number)
            contacts.add(item)
            Log.d("Contact", "$item")

        }

        return contacts
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}