package com.genrikhsalexandr.androidintesive.domain.contact

class EditContactItemUseCase (private val contactsListRepository: ContactsListRepository) {
    fun editContactItem(contactItem: ContactItem) {
        contactsListRepository.editContactItem(contactItem)
  }
}