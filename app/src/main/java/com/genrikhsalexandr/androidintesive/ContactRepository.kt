package com.genrikhsalexandr.androidintesive

import com.genrikhsalexandr.androidintesive.domain.Contact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object ContactRepository {

    private const val MAX_INITIAL_ID = 100

    private val _contactsList: MutableStateFlow<List<Contact>> = MutableStateFlow(emptyList())
    val contactsList: StateFlow<List<Contact>> = _contactsList
    private var autoIncrementId = MAX_INITIAL_ID

    init {
        _contactsList.value = buildList {
            for (a in 1..MAX_INITIAL_ID) {
                val number = (a + 9990000000)
                val item = Contact(
                    id = a,
                    name = " Вася $a",
                    surName = "Иванов $a",
                    number = "+7$number",
                    image = R.drawable.ic_android
                )
                add(item)
            }
        }
    }

    fun addContact(contact: Contact) {
        val id = ++autoIncrementId
        _contactsList.value = _contactsList.value.plus(
            contact.copy(id = id)
        )
    }

    fun deleteContact(contact: Contact) {
        _contactsList.value = contactsList.value.minus(
            contact
        )
    }
}