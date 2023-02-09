package com.josuemartinez.openbrewery.ui.details

import android.app.Application
import androidx.lifecycle.*
import com.josuemartinez.openbrewery.data.database.BreweryDatabase
import com.josuemartinez.openbrewery.data.database.getDatabase
import com.josuemartinez.openbrewery.data.repository.BreweryRepository

class DetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val breweryRepository = BreweryRepository(database)



    private val breweryId = MutableLiveData<String>()
    val brewery = breweryId.switchMap { id ->
        liveData {
            emitSource(breweryRepository.getBrewery(id))
        }
    }

    fun getBrewery(id: Int) {
        breweryId.value = id.toString()
    }




    class DetailsViewModelFactory(val app: Application): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DetailsViewModel(app) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }

}