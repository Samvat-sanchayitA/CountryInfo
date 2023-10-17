package com.samvat.countryinfo.utils

object Constants {
    const val cacheSize = (5 * 1024 * 1024).toLong()
    const val BASE_URL =
        "https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/"

    enum class CountriesApiStatus { LOADING, ERROR, DONE }
}
