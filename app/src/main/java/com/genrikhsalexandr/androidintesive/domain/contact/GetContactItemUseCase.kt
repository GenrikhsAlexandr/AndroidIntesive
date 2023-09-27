package com.genrikhsalexandr.androidintesive.domain.contact

class GetContactItemUseCase(private val contactsListRepository: ContactsListRepository) {

    fun getShopItem(contactItemId: Int): ContactItem {
        return contactsListRepository.getContactItem(contactItemId)
    }
}