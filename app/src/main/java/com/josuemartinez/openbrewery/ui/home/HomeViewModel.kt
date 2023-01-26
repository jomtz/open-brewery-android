package com.josuemartinez.openbrewery.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.josuemartinez.openbrewery.data.database.getDatabase
import com.josuemartinez.openbrewery.data.models.Brewery
import com.josuemartinez.openbrewery.data.repository.BreweryRepository
import kotlinx.coroutines.launch
import java.io.IOException

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * The data source this ViewModel will fetch results from.
     */
    private val database = getDatabase(application)
    private val breweryRepository = BreweryRepository(database)

    /**
     * A brewerylist of breweries displayed on the screen.
     */
    val breweryList = breweryRepository.breweries

    /**
     * A brewerylist of breweries that can be shown on the screen. This is private to avoid exposing a
     * way to set this value to observers.
     */
    private val _breweryList = MutableLiveData<List<Brewery>>()

    /**
     * Event triggered for network error. This is private to avoid exposing a
     * way to set this value to observers.
     */
    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    /**
     * Event triggered for network error. Views should use this to get access
     * to the data.
     */
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    /**
     * Flag to display the error message. This is private to avoid exposing a
     * way to set this value to observers.
     */
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    /**
     * Flag to display the error message. Views should use this to get access
     * to the data.
     */
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    /**
     * init{} is called immediately when this ViewModel is created.
     */
    init {
        refreshDataFromRepository()
    }

    /**
     * Refresh data from the repository. Use a coroutine launch to run in a
     * background thread.
     */
    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                breweryRepository.refreshBreweries()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false

            } catch (networkError: IOException) {
                // Show a Toast error message and hide the progress bar.
                if(breweryList.value.isNullOrEmpty())
                    _eventNetworkError.value = true
            }
        }
    }


    /**
     * Resets the network error flag.
     */
    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

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
