package com.genrikhsalexandr.androidintesive.domain

import android.net.Uri
import android.os.Parcelable
import com.genrikhsalexandr.androidintesive.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val id: Int,
    val name: String,
    val surName: String,
    val number: String,
    val image: Uri?,
    val viewType: Int = R.layout.contact_list_item,
): Parcelable