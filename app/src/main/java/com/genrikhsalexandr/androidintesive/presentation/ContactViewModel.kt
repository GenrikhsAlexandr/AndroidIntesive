package com.genrikhsalexandr.androidintesive.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genrikhsalexandr.androidintesive.ContactRepository
import com.genrikhsalexandr.androidintesive.domain.Contact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class ContactViewModel: ViewModel() {
    private val contactRepository = ContactRepository

    private val contactsList: StateFlow<List<Contact>> = contactRepository.contactsList
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    private val selectedItemsIds: MutableStateFlow<List<Int>> = MutableStateFlow(emptyList())

    fun onItemClicked(item: Contact) {

    }


    fun addContact(contactItem: Contact){
        contactRepository.addContact(contactItem)
    }
}