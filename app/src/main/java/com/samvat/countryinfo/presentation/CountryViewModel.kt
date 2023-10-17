package com.samvat.countryinfo.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.samvat.countryinfo.data.CountryRepository
import com.samvat.countryinfo.model.Country
import com.samvat.countryinfo.utils.Constants.CountriesApiStatus
import com.samvat.countryinfo.utils.Resource
import kotlinx.coroutines.launch

class CountryViewModel(private val repository: CountryRepository) :
    ViewModel() {

     val countries = repository.getCountries().asLiveData()

    class Factory(private val repository: CountryRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CountryViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CountryViewModel(repository) as T
            }
            throw IllegalArgumentException("UNKNOWN VIEW MODEL CLASS")
        }
    }
}