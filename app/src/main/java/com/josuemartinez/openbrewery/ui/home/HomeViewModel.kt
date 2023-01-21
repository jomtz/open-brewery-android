package com.josuemartinez.openbrewery.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.josuemartinez.openbrewery.data.database.BreweryDatabase.Companion.getDatabase
import com.josuemartinez.openbrewery.data.repository.BreweryRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val breweryRepository = BreweryRepository(database)

    lateinit var wordList: MutableList<String>

    init {
        Log.i("GameViewModel", "GameViewModel Created!" )
        viewModelScope.launch {
            breweryRepository.refreshBreweries()
        }

        breweryList()

    }

    fun breweryList() {
        // All the breweries for this List
        wordList = mutableListOf<String>(
            "10-56 Brewing Company",
            "10 Barrel Brewing Co",
            "10 Barrel Brewing Co - Bend Pub",
            "10 Barrel Brewing Co - Boise",
            "10 Barrel Brewing Co - Denver",
            "10 Torr Distilling and Brewing",
            "101 Brewery",
            "101 North Brewing Company",
            "105 West Brewing Co",
            "10K Brewing",
            "10th District Brewing Company",
            "11 Below Brewing Company",
            "1188 Brewing Co",
            "12 Acres Brewing Company",
            "12 Gates Brewing Company",
            "12 West Brewing Company",
            "12 West Brewing Company - Production Facility"
        )

    }
}