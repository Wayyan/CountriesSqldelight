package com.wayyan.countriessqldelight.data.common.repository

import com.wayyan.countriessqldelight.domain.model.CountryModel

interface CountriesNetworkSource {
    fun getAllCountries():List<CountryModel>
}