package com.samvat.countryinfo

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.samvat.countryinfo.presentation.CountryViewModel

class MainActivity : AppCompatActivity() {

//    private val viewModel: CountryViewModel by viewModels {
//        CountryViewModel.Factory((application as CountryInfoApplication).repository)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        viewModel.countries.observe(this) { countries ->
//            countries?.let {
//                Log.i("Am here1", countries.data.toString())
//            }
//        }

    }
}