package com.josmtz.openbrewery.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update

@Dao
interface BreweriesDao {

    @Insert
    fun insert(brewery: Breweries)

    @Update
    fun update(brewery: Breweries)


}