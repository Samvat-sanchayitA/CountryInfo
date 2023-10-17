package com.samvat.countryinfo.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class Country(
    @PrimaryKey
    val name: String,
    val capital: String,
    val code: String,
    val region: String,
    val flag: String
)
