package com.genrikhsalexandr.androidintesive.domain.contact

interface ContactsListRepository {

    fun addContactItem(contactItem: ContactItem)

    fun deleteContactItem(contactItem: ContactItem)

    fun editContactItem(contactItem: ContactItem)

    fun getContactItem(contactItemId: Int): ContactItem

    fun getContactsList():List<ContactItem>
}