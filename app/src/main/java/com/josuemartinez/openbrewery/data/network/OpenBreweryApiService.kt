package com.josuemartinez.openbrewery.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://api.openbrewerydb.org/"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()



interface OpenBreweryApiService {

    @GET("v1/breweries")
    suspend fun getBreweryListAsync(): NetworkBreweryContainer
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object OpenBreweryApi {
    val retrofitService : OpenBreweryApiService by lazy {
        retrofit.create(OpenBreweryApiService::class.java)
    }
}

