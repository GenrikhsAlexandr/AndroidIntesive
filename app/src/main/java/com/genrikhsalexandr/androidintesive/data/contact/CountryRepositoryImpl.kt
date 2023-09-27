package com.genrikhsalexandr.androidintesive.data.contact

import com.genrikhsalexandr.androidintesive.domain.country.CountryItem
import com.genrikhsalexandr.androidintesive.domain.country.CountryListRepository

object CountryRepositoryImpl:CountryListRepository {
    override fun getCountryList(): List<CountryItem> {
        return getCountryList().toList()
    }
}