package com.wayyan.countriessqldelight.data.common.repository

import com.wayyan.countriessqldelight.domain.model.CountryModel
import com.wayyan.countriessqldelight.domain.repository.CountriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CountriesRepositoryImpl constructor(
    private val networkSource: CountriesNetworkSource,
    private val cacheSource: CountriesCacheSource
) : CountriesRepository {
    override fun fetchCountries(): Flow<List<CountryModel>> {
        return flow {
            if (cacheSource.getIsCached()) {
                emit(emptyList<CountryModel>())
            } else {
                val data = networkSource.getAllCountries()
                emit(data)
                if (cacheSource.insertCountries(data)) {
                    emit(emptyList<CountryModel>())
                }
            }
        }
    }

    override fun getCountries(): Flow<List<CountryModel>> {
        return cacheSource.getCountries()
    }
}