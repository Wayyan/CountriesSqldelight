package com.wayyan.countriessqldelight.domain.usecase

import com.wayyan.countriessqldelight.domain.DispatcherProvider
import com.wayyan.countriessqldelight.domain.FlowUseCase
import com.wayyan.countriessqldelight.domain.model.CountryModel
import com.wayyan.countriessqldelight.domain.repository.CountriesRepository
import kotlinx.coroutines.flow.Flow

class FetchAllCountries(
    private val repository: CountriesRepository, dispatcherProvider: DispatcherProvider
) :
    FlowUseCase<Unit, List<CountryModel>>(dispatcherProvider) {
    override suspend fun provide(params: Unit): Flow<List<CountryModel>> {
        return repository.fetchCountries()
    }
}