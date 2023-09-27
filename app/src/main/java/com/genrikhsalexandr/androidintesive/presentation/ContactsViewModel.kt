package com.genrikhsalexandr.androidintesive.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.genrikhsalexandr.androidintesive.data.contact.ContactRepositoryImpl
import com.genrikhsalexandr.androidintesive.domain.contact.ContactItem
import com.genrikhsalexandr.androidintesive.domain.contact.GetContactsListUseCase
import kotlinx.coroutines.flow.MutableStateFlow

class ContactsViewModel : ViewModel() {

    var contactsList: MutableStateFlow<List<ContactItem>> = MutableStateFlow(emptyList())

    private val contactRepository = ContactRepositoryImpl

    private val getContactsListUseCase = GetContactsListUseCase(contactRepository)

    val list = getContactsListUseCase.getContactsList()

}