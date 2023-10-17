package com.samvat.countryinfo

import android.app.Application
import com.samvat.countryinfo.data.CountryRepository
import com.samvat.countryinfo.database.CountryRoomDatabase
import com.samvat.countryinfo.network.CountriesApi

class CountryInfoApplication : Application() {

    private val database by lazy { CountryRoomDatabase.getDatabase(this) }
    private val apiService by lazy { CountriesApi.getCountryApiService(applicationContext) }
    val repository by lazy { CountryRepository(apiService ,database) }
}