package com.josuemartinez.openbrewery.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "brewery_table")
data class Brewery(

    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "brewery_type") val breweryType: String?,
    @ColumnInfo(name = "street") val street: String?,
    @ColumnInfo(name = "address_2") val address2: String?,
    @ColumnInfo(name = "address_3") val address3: String?,
    @ColumnInfo(name = "city") val city: String?,
    @ColumnInfo(name = "state") val state: String?,
    @ColumnInfo(name = "county_province") val countyProvince: String?,
    @ColumnInfo(name = "postal_code") val postalCode: String?,
    @ColumnInfo(name = "country") val country: String?,
    @ColumnInfo(name = "longitude") val longitude: String?,
    @ColumnInfo(name = "latitude") val latitude: String?,
    @ColumnInfo(name = "phone") val phone: String?,
    @ColumnInfo(name = "website_url") val websiteUrl: String?,
    @ColumnInfo(name = "updated_at") val updatedAt: String?,
    @ColumnInfo(name = "created_at") val createdAt: String?
)