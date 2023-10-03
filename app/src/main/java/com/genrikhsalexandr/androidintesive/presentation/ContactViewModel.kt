package com.genrikhsalexandr.androidintesive.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genrikhsalexandr.androidintesive.ContactRepository
import com.genrikhsalexandr.androidintesive.domain.Contact
import com.genrikhsalexandr.androidintesive.domain.ContactItemList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ContactViewModel: ViewModel() {
    private val contactRepository = ContactRepository

    private val _contactsList: StateFlow<List<Contact>> = contactRepository.contactsList

    val contactsList: StateFlow<List<ContactItemList>> = _contactsList.map { contacts ->
        contacts.map { contact ->
            ContactItemList(
                id = contact.id,
                name = contact.name,
                surName = contact.surName,
                contact = contact
            )
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

}