package com.genrikhsalexandr.androidintesive.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genrikhsalexandr.androidintesive.data.contact.ContactRepositoryImpl
import com.genrikhsalexandr.androidintesive.domain.contact.Contact
import com.genrikhsalexandr.androidintesive.domain.contact.ContactItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class ContactViewModel : ViewModel() {

    private val contactRepository = ContactRepositoryImpl

    private val contactsList: StateFlow<List<Contact>> = contactRepository.contactsList
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    private val selectedItemsIds: MutableStateFlow<List<Int>> = MutableStateFlow(emptyList())

    val listItems: StateFlow<List<ContactItem>> = combine(contactsList, selectedItemsIds) { list, ids ->
        list.map {  contact ->
            if (contact.birthDay == null) {
                ContactItem.Short(
                    isSelected = ids.contains(contact.id),
                    contact = contact)
            } else {
                ContactItem.Full(
                    isSelected = ids.contains(contact.id),
                    contact = contact)
            }
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun onItemClicked(item: ContactItem) {
        selectedItemsIds.value = if (selectedItemsIds.value.contains(item.id)) {
            selectedItemsIds.value.minus(item.id)
        } else {
            selectedItemsIds.value.plus(item.id)
        }
    }

    fun delContactItem(contactItem: ContactItem) {
        contactRepository.deleteContact(contactItem.contact)
    }
}