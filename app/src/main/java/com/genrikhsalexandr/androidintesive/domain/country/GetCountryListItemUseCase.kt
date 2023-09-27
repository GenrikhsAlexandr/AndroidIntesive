package com.genrikhsalexandr.androidintesive.domain.country

class GetCountryListItemUseCase(private val countyListRepository: CountryListRepository) {
    fun getCountryList(): List<CountryItem> {
        return countyListRepository.getCountryList()
    }
}