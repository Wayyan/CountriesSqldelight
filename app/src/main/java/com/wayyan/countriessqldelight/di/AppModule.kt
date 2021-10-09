package com.wayyan.countriessqldelight.di

import com.wayyan.countriessqldelight.adapter.CountryRecyclerAdapter
import com.wayyan.countriessqldelight.features.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {
    viewModel {
        MainViewModel(
            fetchAllCountries = get(), getCountries = get()
        )
    }

    single {
        CountryRecyclerAdapter(androidContext())
    }
}