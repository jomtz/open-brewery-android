package com.josuemartinez.openbrewery.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.josuemartinez.openbrewery.data.database.BreweryDatabase
import com.josuemartinez.openbrewery.data.database.asDomainModel
import com.josuemartinez.openbrewery.data.models.Brewery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BreweryRepository(private val database: BreweryDatabase) {

    /**
     * A list of breweries that can be shown on the screen.
     */

    val breweries: LiveData<List<Brewery>> =
        Transformations.map(database.breweryDao.getBreweries()) {
            it.asDomainModel()
        }

    /**
     * Refresh the breweries stored in the offline cache.
     * To actually load the breweries for use, observe [breweries]
     */

    suspend fun refreshBreweries() {
        withContext(Dispatchers.IO) {

        }
    }

}