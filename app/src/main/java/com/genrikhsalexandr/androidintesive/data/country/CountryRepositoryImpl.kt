package com.genrikhsalexandr.androidintesive.data.country

import com.genrikhsalexandr.androidintesive.domain.country.CountryItem
import com.genrikhsalexandr.androidintesive.domain.country.CountryListRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class CountryRepositoryImpl:CountryListRepository {

    companion object {
        private const val BASE_URL = "https://restcountries.com/"
    }

    private val json = Json { ignoreUnknownKeys = true }

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .client(
            OkHttpClient.Builder()
                .apply {
                    addInterceptor(
                        HttpLoggingInterceptor().setLevel(
                            HttpLoggingInterceptor
                                .Level.BODY
                        )
                    )
                }
                .build()
        )
        .build()

    private val service: CountryService = retrofit.create(CountryService::class.java)

    /*suspend fun getCountres():
    {
    }
*/
    override fun getCountryList(): List<CountryItem> {
        return getCountryList().toList()
    }
}