package com.josmtz.openbrewery.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update

@Dao
interface BreweryDao {

    @Insert
    fun insert(brewery: Brewery)

    @Update
    fun update(brewery: Brewery)


}