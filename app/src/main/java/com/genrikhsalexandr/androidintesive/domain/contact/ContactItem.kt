package com.genrikhsalexandr.androidintesive.domain.contact

import com.genrikhsalexandr.androidintesive.R

sealed interface ContactItem {

    val contact: Contact
    val id: Int
    val name: String
    val surName: String
    val number: String
    var isSelected: Boolean
    val viewType: Int

    data class Full(
        override val contact: Contact,
        override var isSelected: Boolean,
    ) : ContactItem {

        override val id: Int get() = contact.id
        override val name: String get() = contact.name
        override val surName: String get() = contact.surName
        override val number: String get() = contact.number

        val birthDay: String get() = contact.birthDay!!

        override val viewType: Int = R.layout.list_item_contacts_full
    }
    data class Short(
        override val contact: Contact,
        override var isSelected: Boolean,
    ) : ContactItem {

        override val id: Int get() = contact.id
        override val name: String get() = contact.name
        override val surName: String get() = contact.surName
        override val number: String get() = contact.number

        override val viewType: Int = R.layout.list_item_contacts_short
    }
}