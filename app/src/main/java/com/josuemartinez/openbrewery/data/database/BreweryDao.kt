package com.josuemartinez.openbrewery.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.josuemartinez.openbrewery.data.models.Brewery

@Dao
interface BreweryDao {

    @Query("select * from databaseBrewery")
    fun getBreweries(): LiveData<List<DatabaseBrewery>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg videos: DatabaseBrewery)

    @Update
    fun update(brewery: Brewery)


}