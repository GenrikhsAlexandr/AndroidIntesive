package com.genrikhsalexandr.androidintesive

import com.genrikhsalexandr.androidintesive.domain.Contact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object ContactRepository {

    private const val MAX_INITIAL_ID = 100

    private val _contactsList: MutableStateFlow<List<Contact>> = MutableStateFlow(emptyList())
    val contactsList: StateFlow<List<Contact>> = _contactsList

    init {
        _contactsList.value = buildList {
            for (a in 1..MAX_INITIAL_ID) {
                val number = (a + 9990000000)
                val item = Contact(
                    id = a,
                    name = " Вася $a",
                    surName = "Иванов $a",
                    number = "+7$number",
                    image = null
                )
                add(item)
            }
        }
    }

    fun updateContact(updatedContact: Contact) {
        _contactsList.value = _contactsList.value.map { contact ->
            if (contact.id == updatedContact.id) {
                updatedContact
            } else {
                contact
            }
        }
    }

    fun getContact(contactId: Int): Contact {
        return _contactsList.value.first { contact ->
            contact.id == contactId
        }
    }
}