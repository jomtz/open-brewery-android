package com.josuemartinez.openbrewery.data.network

import com.josuemartinez.openbrewery.data.database.DatabaseBrewery
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass



//@JsonClass(generateAdapter = true)
//data class NetworkBreweryContainer(val breweries: List<NetworkBrewery>)

//class NetworkBreweryContainer(val breweries: List<NetworkBrewery>)
typealias NetworkBreweryContainer = List<NetworkBrewery>



@JsonClass(generateAdapter = true)
data class NetworkBrewery(
        @Json(name = "id")
        val id: String,
        @Json(name = "name")
        val name: String,
        @Json(name = "brewery_type")
        val breweryType: String?,
        @Json(name = "street")
        val street: String?,
        @Json(name = "address_2")
        val address2: String?,
        @Json(name = "address_3")
        val address3: String?,
        @Json(name = "city")
        val city: String?,
        @Json(name = "state")
        val state: String?,
        @Json(name = "county_province")
        val countyProvince: String?,
        @Json(name = "postal_code")
        val postalCode: String?,
        @Json(name = "country")
        val country: String?,
        @Json(name = "longitude")
        val longitude: String?,
        @Json(name = "latitude")
        val latitude: String?,
        @Json(name = "phone")
        val phone: String?,
        @Json(name = "website_url")
        val websiteUrl: String?,
        @Json(name = "updated_at")
        val updatedAt: String?,
        @Json(name = "created_at")
        val createdAt: String?
        )

/**
 * Convert Network results to database objects
 */





//fun NetworkBreweryContainer.asDomainModel(): List<Brewery> {
//    return breweries.map {
//        Brewery(
//            id = it.id,
//            name = it.name,
//            breweryType = it.breweryType,
//            street = it.street,
//            address2 = it.address2,
//            address3 = it.address3,
//            city = it.city,
//            state = it.state,
//            countyProvince = it.countyProvince,
//            postalCode = it.postalCode,
//            country = it.country,
//            longitude =it.longitude,
//            latitude = it.latitude,
//            phone = it.phone,
//            websiteUrl = it.websiteUrl,
//            updatedAt = it.updatedAt,
//            createdAt = it.createdAt)
//    }
//}

fun NetworkBreweryContainer.asDatabaseModel(): List<DatabaseBrewery>   {
    return map {
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
                createdAt = it.createdAt
        )
    }
}

