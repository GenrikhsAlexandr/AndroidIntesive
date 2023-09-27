package com.genrikhsalexandr.androidintesive.domain.country

interface CountryListRepository {
    fun getCountryList(): List<CountryItem>
}