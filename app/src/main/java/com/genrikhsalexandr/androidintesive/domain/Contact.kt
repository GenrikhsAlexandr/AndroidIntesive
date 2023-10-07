package com.genrikhsalexandr.androidintesive.domain

import com.genrikhsalexandr.androidintesive.R


data class Contact(
    val id: Int,
    val name: String,
    val surName: String,
    val number: String,
    val image: Int,
    val viewType: Int = R.layout.contact_list_item,
)