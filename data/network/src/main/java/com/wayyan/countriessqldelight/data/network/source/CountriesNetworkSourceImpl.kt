package com.wayyan.countriessqldelight.data.network.source

import com.wayyan.countriessqldelight.data.common.repository.CountriesNetworkSource
import com.wayyan.countriessqldelight.data.network.api.CountryApiServices
import com.wayyan.countriessqldelight.data.network.exceptions.NoContentException
import com.wayyan.countriessqldelight.data.network.extensions.executeOrThrow
import com.wayyan.countriessqldelight.domain.model.CountryModel
import com.wayyan.countriessqldelight.domain.model.K

class CountriesNetworkSourceImpl constructor(private val apiServices: CountryApiServices) :
    CountriesNetworkSource {
    override fun getAllCountries(): List<CountryModel> {
        val rawResponse = apiServices.getCountryList()
            .executeOrThrow()
        return if (rawResponse.isNullOrEmpty()) {
            throw NoContentException()
        } else {
            rawResponse.map {
                it.mapToCountryModel()
            }
        }
    }
}