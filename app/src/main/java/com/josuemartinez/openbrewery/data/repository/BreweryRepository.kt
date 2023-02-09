package com.josuemartinez.openbrewery.data.repository

import androidx.lifecycle.LiveData
import com.josuemartinez.openbrewery.data.database.BreweryDatabase
import com.josuemartinez.openbrewery.data.database.DatabaseBrewery
import com.josuemartinez.openbrewery.data.network.OpenBreweryApi.retrofitService
import com.josuemartinez.openbrewery.data.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BreweryRepository(private val database: BreweryDatabase) {

    /**
     * A list of breweries that can be shown on the screen.
     */

    val breweries: LiveData<List<DatabaseBrewery>> = database.breweryDao.getAllBreweries()

    fun getBrewery(id: String) = database.breweryDao.getBreweryById(id)
    /**
     * Refresh the breweries stored in the offline cache.
     * To actually load the breweries for use, observe [breweries]
     */

    suspend fun refreshBreweries() {
            withContext(Dispatchers.IO) {
                val breweryResponse = retrofitService.getBreweryListAsync()
                database.breweryDao.insertBreweries(breweryResponse.asDatabaseModel())
            }

    }


}