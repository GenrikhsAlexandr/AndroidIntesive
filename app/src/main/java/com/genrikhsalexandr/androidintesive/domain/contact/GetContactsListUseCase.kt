package com.genrikhsalexandr.androidintesive.domain.contact

class GetContactsListUseCase (private val contactsListRepository: ContactsListRepository) {

    fun getContactsList():List<ContactItem>{
       return contactsListRepository.getContactsList()
    }
}