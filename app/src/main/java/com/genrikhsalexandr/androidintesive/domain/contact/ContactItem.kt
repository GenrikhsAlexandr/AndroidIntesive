package com.genrikhsalexandr.androidintesive.domain.contact

data class ContactItem(
    val name:String,
    val surName:String,
    val number: String,
    val enabled:Boolean,
    var id:Int = UNDEFINED_ID

    )

{
    companion object{

        const val UNDEFINED_ID = -1
    }
}
