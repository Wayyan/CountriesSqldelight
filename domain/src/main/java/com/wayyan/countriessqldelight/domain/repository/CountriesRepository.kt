package com.wayyan.countriessqldelight.domain.repository

import com.wayyan.countriessqldelight.domain.model.CountryModel
import kotlinx.coroutines.flow.Flow

interface CountriesRepository {
    fun fetchCountries(): Flow<List<CountryModel>>
    fun getCountries(): Flow<List<CountryModel>>
}