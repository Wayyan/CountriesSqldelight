package com.wayyan.countriessqldelight.data.network.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.wayyan.countriessqldelight.domain.model.CountryModel

@JsonClass(generateAdapter = true)
data class CountriesResponse(
    @Json(name = "data") val data: List<CountryResponse>
)

@JsonClass(generateAdapter = true)
data class CountryResponse(
    @Json(name = "countryName") val name: String,
    @Json(name = "currencyName") val capital: String?,
    @Json(name = "isCountry") val isCountry: Boolean
) {
    fun mapToCountryModel(): CountryModel {
        return CountryModel(
            name = name,
            capital = capital ?: "-",
            isCountry = isCountry
        )
    }
}