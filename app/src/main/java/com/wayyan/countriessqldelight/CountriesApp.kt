package com.wayyan.countriessqldelight

import android.app.Application
import com.wayyan.countriessqldelight.base.BaseAppModule
import com.wayyan.countriessqldelight.data.cache.di.CacheModule
import com.wayyan.countriessqldelight.data.common.di.DataModule
import com.wayyan.countriessqldelight.data.network.di.NetworkModule
import com.wayyan.countriessqldelight.di.AppModule
import com.wayyan.countriessqldelight.domain.di.DomainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class CountriesApp : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()

        setUpKoin()
    }

    private fun setUpKoin() {
        startKoin {
            androidContext(this@CountriesApp)
            androidLogger()

            modules(
                listOf(
                    DomainModule,
                    DataModule,
                    CacheModule,
                    NetworkModule,
                    BaseAppModule,
                    AppModule
                )
            )
        }
    }
}