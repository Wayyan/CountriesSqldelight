package com.wayyan.countriessqldelight.domain.di

import com.wayyan.countriessqldelight.domain.usecase.FetchAllCountries
import com.wayyan.countriessqldelight.domain.usecase.GetCountries
import org.koin.dsl.module

val DomainModule = module {
    single {
        FetchAllCountries(get(), get())
    }

    single {
        GetCountries(get(), get())
    }
}