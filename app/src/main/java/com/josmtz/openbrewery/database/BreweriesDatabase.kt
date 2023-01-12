package com.josmtz.openbrewery.database

import android.content.Context
import androidx.room.*

@Database(version = 1, entities = [Breweries::class], exportSchema = false)
abstract class BreweriesDatabase : RoomDatabase() {

    abstract val breweriesDao: BreweriesDao

    companion object{

        @Volatile
        private var INSTANCE: BreweriesDatabase? = null

        fun getInstance(context: Context): BreweriesDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BreweriesDatabase::class.java,
                        "brewery_listing_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}