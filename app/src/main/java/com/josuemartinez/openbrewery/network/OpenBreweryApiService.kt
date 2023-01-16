package com.josuemartinez.openbrewery.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://api.openbrewerydb.org/"

/**
 * Use the Retrofit builder to build a retrofit object using a Scalars converter.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()




interface OpenBreweryApiService {

    @GET("breweries")
    suspend fun getProperties(@Query("filter") type: String): List<OpenBreweryData>
    fun getProperties():
            Call<String>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object OpenBreweryApi {
    val retrofitService : OpenBreweryApiService by lazy { retrofit.create(OpenBreweryApiService::class.java) }
}

