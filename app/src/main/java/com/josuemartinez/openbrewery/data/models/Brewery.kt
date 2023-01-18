package com.josuemartinez.openbrewery.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "brewery_listing_table")
data class Brewery(

    @PrimaryKey(autoGenerate = true)
    var key: Long = 0L,

    val id: String,

    val name: String,

    val brewery_type: String,

    val street: String,

    val address_2: String,

    val address_3: String,

    val city: String,

    val state: String,

    val county_province: String,

    val postal_code: String,

    val country: String,

    val longitude: String,

    val latitude: String,

    val phone: String,

    val website_url: String,

    //"updated_at": "2023-01-04T04:46:02.393Z",
    //"created_at": "2023-01-04T04:46:02.393Z"
)