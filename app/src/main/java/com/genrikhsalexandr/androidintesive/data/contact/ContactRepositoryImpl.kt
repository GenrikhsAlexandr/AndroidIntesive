package com.genrikhsalexandr.androidintesive.data.contact

import android.util.Log
import com.genrikhsalexandr.androidintesive.domain.contact.ContactItem
import com.genrikhsalexandr.androidintesive.domain.contact.ContactsListRepository

object ContactRepositoryImpl : ContactsListRepository {

    private val contactsList = mutableListOf<ContactItem>()

    private var autoIncrementId = 0

    fun generateContacts(): List<ContactItem> {
        val contacts = mutableListOf<ContactItem>()

        for (a in 1..100) {
            val number = (a + 9990000000).toString()
            val item = ContactItem(" Вася $a", "Иванов $a", "+7$number",true)
            contacts.add(item)
            Log.d("Contact", "$item")

        }

        return contacts
    }

    override fun addContactItem(contactItem: ContactItem) {
        if (contactItem.id==ContactItem.UNDEFINED_ID)
        {contactItem.id = autoIncrementId++}
        contactsList.add(contactItem)
        Log.d("Contact", "addContactItem $contactItem")

    }

    override fun deleteContactItem(contactItem: ContactItem) {
        contactsList.remove(contactItem)
    }

    override fun editContactItem(contactItem: ContactItem) {
        val oldContactItem = getContactItem(contactItem.id)
        contactsList.remove(oldContactItem)
        addContactItem(contactItem)
    }

    override fun getContactItem(contactItemId: Int): ContactItem {
        return contactsList.find {
            it.id == contactItemId
        } ?: throw RuntimeException("Element with $contactItemId not found")
    }

    override fun getContactsList(): List<ContactItem> {
        return contactsList.toList()
    }
}