package com.genrikhsalexandr.androidintesive.data.country

import retrofit2.http.GET

interface CountryService {
    @GET("v3/all")
    fun getAllCountries(): List<CountryDto>
}