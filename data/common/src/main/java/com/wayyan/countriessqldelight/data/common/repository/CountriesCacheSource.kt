package com.wayyan.countriessqldelight.data.common.repository

import com.wayyan.countriessqldelight.domain.model.CountryModel
import kotlinx.coroutines.flow.Flow

interface CountriesCacheSource {
    fun insertCountries(data: List<CountryModel>):Boolean
    fun getCountries(): Flow<List<CountryModel>>
    fun setIsCached(data: Boolean)
    fun getIsCached(): Boolean
}