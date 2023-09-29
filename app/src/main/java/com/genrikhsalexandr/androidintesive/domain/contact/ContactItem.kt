package com.genrikhsalexandr.androidintesive.domain.contact

import com.genrikhsalexandr.androidintesive.R

sealed interface ContactItem {

    val name: String
    val surName: String
    val number: String
    val isSelected: Boolean
    val viewType: Int
    var id: Int

    data class Full(
        override val name: String,
        override val surName: String,
        override val number: String,
        override val isSelected: Boolean,
        val birthDay: String,
        override var id: Int = UNDEFINED_ID,

        ) : ContactItem {
        override val viewType: Int = R.layout.list_item_contacts_full
    }
    data class Short(

        override val name: String,
        override val surName: String,
        override val number: String,
        override val isSelected: Boolean,
        override var id: Int = UNDEFINED_ID,
    ) : ContactItem {
        override val viewType: Int = R.layout.list_item_contacts_short
    }

        companion object{

        const val UNDEFINED_ID = -1
    }
}