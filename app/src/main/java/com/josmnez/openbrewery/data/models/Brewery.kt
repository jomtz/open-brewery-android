package com.josmnez.openbrewery.data.models

/**
 * Domain objects are plain Kotlin data classes that represent the things in our app. These are the
 * objects that should be displayed on screen, or manipulated by the app.
 *
 * @see database for objects that are mapped to the database
 * @see network for objects that parse or prepare network calls
 */

/**
 * Breweries represent an actual Brewery that can be found on a List.
 */
data class Brewery(val id: String,
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