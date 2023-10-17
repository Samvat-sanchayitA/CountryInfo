package com.samvat.countryinfo.network

import android.content.Context
import com.samvat.countryinfo.model.Country
import com.samvat.countryinfo.utils.Constants.BASE_URL
import com.samvat.countryinfo.utils.provideCache
import com.samvat.countryinfo.utils.provideOkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CountriesApiService {
    @GET("countries.json")
    suspend fun getCountries(): List<Country>
}

object CountriesApi {

    fun getCountryApiService(context: Context): CountriesApiService {
        val cache = provideCache(context)
        val okHttpClient = provideOkHttpClient(context, cache)
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
        return retrofit.create(CountriesApiService::class.java)
    }
}