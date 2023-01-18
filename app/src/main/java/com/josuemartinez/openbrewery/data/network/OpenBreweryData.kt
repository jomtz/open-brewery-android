package com.josuemartinez.openbrewery.data.network

import android.content.res.Resources
import com.josuemartinez.openbrewery.data.models.Brewery


fun OpenBreweryData(resources: Resources): List<Brewery>{
    return listOf(
        Brewery(
            id = "10-56-brewing-company-knox",
            name = "10-56 Brewing Company",
            breweryType ="micro",
            street = "400 Brown Cir",
            address2 =	null,
            address3 =	null,
            city =	"Knox",
            state =	"Indiana",
            countyProvince = null,
            postalCode = "46534",
            country = "United States",
            longitude = "-86.627954",
            latitude = "41.289715",
            phone = "6308165790",
            websiteUrl = null,
            updatedAt =	"2023-01-04T04:46:02.393Z",
            createdAt = "2023-01-04T04:46:02.393Z"
        )
    )
}

