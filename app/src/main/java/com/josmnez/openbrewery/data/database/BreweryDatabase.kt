package com.josmnez.openbrewery.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [DatabaseBrewery::class], exportSchema = false)
abstract class BreweryDatabase : RoomDatabase() {

    abstract val breweryDao: BreweryDao
}

private lateinit var INSTANCE: BreweryDatabase

fun getDatabase(context: Context): BreweryDatabase {
    synchronized(BreweryDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                BreweryDatabase::class.java,
                "brewery_database"
            ).build()
        }
    }
    return INSTANCE
}
