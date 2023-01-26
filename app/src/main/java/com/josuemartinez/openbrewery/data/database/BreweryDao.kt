package com.josuemartinez.openbrewery.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BreweryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBreweries(breweries: List<DatabaseBrewery>)

    @Query("select * from brewery_table")
    fun getBreweries(): LiveData<List<DatabaseBrewery>>



}