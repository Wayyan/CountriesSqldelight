package com.wayyan.countriessqldelight.data.cache.source

import android.content.Context
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.wayyan.countriessqldelight.data.cache.db.CountriesDb
import com.wayyan.countriessqldelight.data.cache.extensions.mapToCountryModel
import com.wayyan.countriessqldelight.data.common.repository.CountriesCacheSource
import com.wayyan.countriessqldelight.domain.model.CountryModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CountriesCacheSourceImpl constructor(
    private val context: Context, private val database: CountriesDb
) : CountriesCacheSource {
    private val sharedPreferences = context.getSharedPreferences("country", Context.MODE_PRIVATE)
    private val KEY_MARK_CACHED = "Cached"

    override fun insertCountries(data: List<CountryModel>): Boolean {
        data.forEach {
            val isCountry = if (it.isCountry)
                1
            else
                0
            database.countriesTableQueries.insert(
                name = it.name,
                currency = it.capital,
                isCountry = isCountry.toLong()
            )
        }
        setIsCached(true)
        return true
    }

    override fun getCountries(): Flow<List<CountryModel>> {
        return database.countriesTableQueries.selectAll()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map {
                it.map {
                    it.mapToCountryModel()
                }
            }
    }

    override fun setIsCached(data: Boolean) {
        sharedPreferences.edit().putBoolean(KEY_MARK_CACHED, data).apply()
    }

    override fun getIsCached(): Boolean {
        return sharedPreferences.getBoolean(KEY_MARK_CACHED, false)
    }
}