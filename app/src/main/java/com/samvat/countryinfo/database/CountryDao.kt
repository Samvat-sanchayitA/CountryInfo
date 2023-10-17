package com.samvat.countryinfo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.samvat.countryinfo.model.Country
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDAO {

    @Insert(entity = Country::class,onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCountries(countries: List<Country>)

    @Query("SELECT * FROM countries")
     fun getCountries() : Flow<List<Country>>

    @Query("DELETE FROM countries")
    fun deleteAll()

}