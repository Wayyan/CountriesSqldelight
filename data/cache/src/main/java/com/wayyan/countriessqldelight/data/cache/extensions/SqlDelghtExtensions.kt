package com.wayyan.countriessqldelight.data.cache.extensions

import com.wayyan.countriessqldelight.data.cache.models.db.CountriesTable
import com.wayyan.countriessqldelight.domain.model.CountryModel

fun CountriesTable.mapToCountryModel(): CountryModel {
    val isCountry = this.isCountry != (0).toLong()
    return CountryModel(name = this.name, capital = this.currency, isCountry = isCountry)
}