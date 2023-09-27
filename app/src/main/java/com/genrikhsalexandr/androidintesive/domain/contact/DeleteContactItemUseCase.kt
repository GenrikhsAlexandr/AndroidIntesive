package com.genrikhsalexandr.androidintesive.domain.contact

class DeleteContactItemUseCase (private val contactsListRepository: ContactsListRepository) {
    fun deleteContactItem(contactItem: ContactItem) {
        contactsListRepository.deleteContactItem(contactItem)
   }
}