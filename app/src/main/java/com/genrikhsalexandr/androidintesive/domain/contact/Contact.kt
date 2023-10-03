package com.genrikhsalexandr.androidintesive.domain.contact

data class Contact(
    val id: Int = UNDEFINED_ID,
    val name: String,
    val surName: String,
    val number: String,
    val birthDay: String?,
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}