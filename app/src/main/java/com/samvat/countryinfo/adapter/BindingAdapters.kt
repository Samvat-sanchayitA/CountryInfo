package com.samvat.countryinfo.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("countryName", "countryRegion")
fun bindImage(textView: TextView, name: String, region: String) {
    val countryName = "$name, $region"
    textView.text = countryName
}