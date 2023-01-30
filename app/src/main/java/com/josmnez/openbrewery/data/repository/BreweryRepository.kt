package com.josmnez.openbrewery.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.josmnez.openbrewery.data.database.BreweryDatabase
import com.josmnez.openbrewery.data.database.asDomainModel
import com.josmnez.openbrewery.data.models.Brewery
import com.josmnez.openbrewery.data.network.OpenBreweryApi.retrofitService
import com.josmnez.openbrewery.data.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BreweryRepository(private val database: BreweryDatabase) {

    /**
     * A list of breweries that can be shown on the screen.
     */

    val breweries: LiveData<List<Brewery>> =
        Transformations.map(database.breweryDao.getAllBreweries()) {
            it.asDomainModel()
        }

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