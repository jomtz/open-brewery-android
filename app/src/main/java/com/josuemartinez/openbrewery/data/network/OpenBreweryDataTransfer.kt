package com.josuemartinez.openbrewery.data.network

import com.josuemartinez.openbrewery.data.database.DatabaseBrewery
import com.josuemartinez.openbrewery.data.models.Brewery
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkBreweryContainer(val breweries: List<NetworkBrewery>)


@JsonClass(generateAdapter = true)
data class NetworkBrewery(
        val id: String,
        val name: String,
        val breweryType: String?,
        val street: String?,
        val address2: String?,
        val address3: String?,
        val city: String?,
        val state: String?,
        val countyProvince: String?,
        val postalCode: String?,
        val country: String?,
        val longitude: String?,
        val latitude: String?,
        val phone: String?,
        val websiteUrl: String?,
        val updatedAt: String?,
        val createdAt: String?)

/**
 * Convert Network results to database objects
 */
fun NetworkBreweryContainer.asDomainModel(): List<Brewery> {
    return breweries.map {
        Brewery(
            id = it.id,
            name = it.name,
            breweryType = it.breweryType,
            street = it.street,
            address2 = it.address2,
            address3 = it.address3,
            city = it.city,
            state = it.state,
            countyProvince = it.countyProvince,
            postalCode = it.postalCode,
            country = it.country,
            longitude =it.longitude,
            latitude = it.latitude,
            phone = it.phone,
            websiteUrl = it.websiteUrl,
            updatedAt = it.updatedAt,
            createdAt = it.createdAt)
    }
}

fun NetworkBreweryContainer.asDatabaseModel(): Array<DatabaseBrewery> {
    return breweries.map {
        DatabaseBrewery(
                id = it.id,
                name = it.name,
                breweryType = it.breweryType,
                street = it.street,
                address2 = it.address2,
                address3 = it.address3,
                city = it.city,
                state = it.state,
                countyProvince = it.countyProvince,
                postalCode = it.postalCode,
                country = it.country,
                longitude =it.longitude,
                latitude = it.latitude,
                phone = it.phone,
                websiteUrl = it.websiteUrl,
                updatedAt = it.updatedAt,
                createdAt = it.createdAt)
    }.toTypedArray()
}

