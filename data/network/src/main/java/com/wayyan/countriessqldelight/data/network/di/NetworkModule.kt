package com.wayyan.countriessqldelight.data.network.di

import com.wayyan.countriessqldelight.data.common.repository.CountriesNetworkSource
import com.wayyan.countriessqldelight.data.network.api.CountryApiServices
import com.wayyan.countriessqldelight.data.network.source.CountriesNetworkSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val NetworkModule = module {
    single<CountryApiServices> {
        RetrofitProvider.retrofit(androidContext()).create(CountryApiServices::class.java)
    }

    single<CountriesNetworkSource> {
        CountriesNetworkSourceImpl(apiServices = get())
    }
}