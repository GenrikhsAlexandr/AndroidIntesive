package com.genrikhsalexandr.androidintesive.domain

import android.net.Uri
import com.genrikhsalexandr.androidintesive.R

data class Contact(
    val id: Int,
    val name: String,
    val surName: String,
    val number: String,
    val image: Uri?,
    val viewType: Int = R.layout.contact_list_item,
)