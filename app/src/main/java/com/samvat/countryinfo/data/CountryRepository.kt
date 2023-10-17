package com.samvat.countryinfo.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.room.withTransaction
import com.samvat.countryinfo.database.CountryDAO
import com.samvat.countryinfo.database.CountryRoomDatabase
import com.samvat.countryinfo.model.Country
import com.samvat.countryinfo.network.CountriesApi
import com.samvat.countryinfo.network.CountriesApiService
import com.samvat.countryinfo.utils.networkBoundResource
import kotlinx.coroutines.delay

class CountryRepository (private val countriesApi: CountriesApiService,
private val database: CountryRoomDatabase ){

   private val dao = database.countryDao()

    fun getCountries() = networkBoundResource(
        // pass in the logic to query data from the database
        query = {
            dao.getCountries()
        },
        // pass in the logic to fetch data from the api
        fetch = {
            delay(2000)
            countriesApi.getCountries()

        },

        //pass in the logic to save the result to the local cache
        saveFetchResult = { countries ->
            database.withTransaction {
                dao.deleteAll()
                dao.addCountries(countries)
            }
        },

        //pass in the logic to determine if the networking call should be made
        shouldFetch = {countries ->
            countries.isEmpty()
        }
    )
//    suspend fun getCountries() {
//        try {
//            val countryList = countriesApi.getCountries()
//            dao.addCountries(countryList)
//            dao.addCountry(countryList[0])
//            _countries.value = dao.getCountries().asLiveData()
//            Log.i("Am here1", dao.getCountries().toString())
//        } catch (e : Exception){
//            _countries.value = listOf()
//        }
//
//    }
}