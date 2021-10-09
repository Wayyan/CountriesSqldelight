package com.wayyan.countriessqldelight.data.common.di

import com.wayyan.countriessqldelight.data.common.repository.CountriesRepositoryImpl
import com.wayyan.countriessqldelight.domain.repository.CountriesRepository
import org.koin.dsl.module

val DataModule = module {
    single<CountriesRepository> {
        CountriesRepositoryImpl(get(), get())
    }
}