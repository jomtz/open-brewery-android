package com.josuemartinez.openbrewery.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import com.josuemartinez.openbrewery.model.models.Brewery

@Dao
interface BreweryDao {

    @Insert
    fun insert(brewery: Brewery)

    @Update
    fun update(brewery: Brewery)


}