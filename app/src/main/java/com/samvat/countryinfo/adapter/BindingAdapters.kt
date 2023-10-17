package com.samvat.countryinfo.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("countryName", "countryRegion")
fun bindImage(textView: TextView, name: String, region:String) {
    textView.text = "$name, $region"
}