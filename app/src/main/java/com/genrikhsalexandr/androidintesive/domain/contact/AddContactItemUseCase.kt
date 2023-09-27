package com.genrikhsalexandr.androidintesive.domain.contact

class AddContactItemUseCase(private val contactsListRepository: ContactsListRepository) {
    fun addContactItem(contactItem: ContactItem) {
        contactsListRepository.addContactItem(contactItem)
   }
}