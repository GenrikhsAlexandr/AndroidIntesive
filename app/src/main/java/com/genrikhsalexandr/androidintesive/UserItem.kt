package com.genrikhsalexandr.androidintesive

data class UserItem(
    val name: String,
    val surName: String,
    val number: String,
    val image: Int,
    var id: Int = UNDEFINED_ID,
) {
    companion object {

        const val UNDEFINED_ID = -1
    }
}