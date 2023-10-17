package com.samvat.countryinfo

import android.app.Application
import android.util.Log
import com.samvat.countryinfo.data.CountryRepository
import com.samvat.countryinfo.database.CountryRoomDatabase
import com.samvat.countryinfo.network.CountriesApi
import com.samvat.countryinfo.network.CountriesApiService
import com.samvat.countryinfo.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountryInfoApplication : Application() {

    val database by lazy { CountryRoomDatabase.getDatabase(this) }
    val apiService by lazy { CountriesApi.getCountryApiService(applicationContext) }
    val repository by lazy { CountryRepository(apiService ,database) }
}