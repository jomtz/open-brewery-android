package com.josuemartinez.openbrewery.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.josuemartinez.openbrewery.data.database.getDatabase
import com.josuemartinez.openbrewery.data.repository.BreweryRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val breweryRepository = BreweryRepository(database)


    init {
        Log.i("GameViewModel", "GameViewModel Created!" )
        viewModelScope.launch {
            breweryRepository.refreshBreweries()
        }
    }

    val breweryList = breweryRepository.breweries


    /**
     * HomeViewModelFactory for constructing HomeViewModel with parameter
     */

    class HomeViewModelFactory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HomeViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
