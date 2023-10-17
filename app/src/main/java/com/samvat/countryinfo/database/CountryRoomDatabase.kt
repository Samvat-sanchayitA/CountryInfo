package com.samvat.countryinfo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.samvat.countryinfo.model.Country

@Database(entities = [Country::class], version = 1, exportSchema = false)
abstract class CountryRoomDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDAO

    companion object {
        @Volatile
        private var INSTANCE: CountryRoomDatabase? = null

        fun getDatabase(
            context: Context,
        ): CountryRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CountryRoomDatabase::class.java,
                    "country_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
