package com.samvat.countryinfo.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.samvat.countryinfo.network.CountriesApiService
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun hasNetwork(context: Context): Boolean {
    var isConnected = false // Initial Value
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

    if (capabilities != null) {
        if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
            isConnected = true
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
            isConnected = true
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
            isConnected = true
        }
    }
    return isConnected
}

fun provideOkHttpClient(context: Context, cache: Cache) =
    OkHttpClient.Builder()
        .cache(cache)
        .addInterceptor { chain ->
            var request = chain.request()
            request = if (hasNetwork(context))
            /*
        *  If there is Internet, get the cache that was stored 5 seconds ago.
        *  If the cache is older than 5 seconds, then discard it,
        *  and indicate an error in fetching the response.
        *  The 'max-age' attribute is responsible for this behavior.
        */
                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
            else
            /*
        *  If there is no Internet, get the cache that was stored 7 days ago.
        *  If the cache is older than 7 days, then discard it,
        *  and indicate an error in fetching the response.
        *  The 'max-stale' attribute is responsible for this behavior.
        *  The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.
        */
                request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                ).build()

            chain.proceed(request)
        }
        .build()

fun provideCache(context: Context) = Cache(context.cacheDir, Constants.cacheSize)

fun provideRetrofit(httpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(Constants.BASE_URL)
    .client(httpClient)
    .build()

fun provideApiService(retrofit: Retrofit): CountriesApiService =
    retrofit.create(CountriesApiService::class.java)






