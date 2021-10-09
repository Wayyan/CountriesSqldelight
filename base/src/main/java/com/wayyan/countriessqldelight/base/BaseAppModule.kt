package com.wayyan.countriessqldelight.base

import com.wayyan.countriessqldelight.domain.DispatcherProvider
import org.koin.dsl.module

val BaseAppModule = module {
    single<DispatcherProvider> {
        DefaultDispatcherProvider()
    }
}