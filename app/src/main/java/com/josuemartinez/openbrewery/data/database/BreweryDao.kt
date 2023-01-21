package com.josuemartinez.openbrewery.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.josuemartinez.openbrewery.data.models.Brewery

@Dao
interface BreweryDao {

    @Query("select * from databaseBrewery")
    fun getBreweries(): LiveData<List<DatabaseBrewery>>

    @Insert
    fun insert(brewery: Brewery)

    @Update
    fun update(brewery: Brewery)


}