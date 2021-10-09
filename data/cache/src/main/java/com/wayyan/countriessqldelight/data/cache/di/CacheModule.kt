package com.wayyan.countriessqldelight.data.cache.di

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.wayyan.countriessqldelight.data.cache.db.CountriesDb
import com.wayyan.countriessqldelight.data.cache.source.CountriesCacheSourceImpl
import com.wayyan.countriessqldelight.data.common.repository.CountriesCacheSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val CacheModule = module {
    single<SqlDriver> {
        createSqlDriver(androidContext())
    }

    single {
        createDatabase(get())
    }

    single<CountriesCacheSource> {
        CountriesCacheSourceImpl(androidContext(), get())
    }
}

fun createSqlDriver(context: Context) =
    AndroidSqliteDriver(
        schema = CountriesDb.Schema, context = context, name = "CountriesDb.db"
    )

fun createDatabase(sqlDriver: SqlDriver) = CountriesDb(
    driver = sqlDriver
)