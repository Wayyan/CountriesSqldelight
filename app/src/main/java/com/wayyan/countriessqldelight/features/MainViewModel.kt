package com.wayyan.countriessqldelight.features

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wayyan.countriessqldelight.base.helper.AsyncViewStateLiveData
import com.wayyan.countriessqldelight.domain.model.CountryModel
import com.wayyan.countriessqldelight.domain.usecase.FetchAllCountries
import com.wayyan.countriessqldelight.domain.usecase.GetCountries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel constructor(
    private val fetchAllCountries: FetchAllCountries,
    private val getCountries: GetCountries
) : ViewModel() {
    val fetchAllCountriesLiveData = AsyncViewStateLiveData<List<CountryModel>>()
    val getCountriesLiveData = AsyncViewStateLiveData<List<CountryModel>>()

    fun fetchAllCountries() {
        Timber.d("Ok")
        fetchAllCountriesLiveData.postLoading()
        viewModelScope.launch {
            val result = kotlin.runCatching {
                fetchAllCountries.execute(Unit)
                    .flowOn(Dispatchers.IO)
                    .collect {
                        fetchAllCountriesLiveData.postSuccess(it)
                    }
            }

            result.exceptionOrNull()?.let {
                it.printStackTrace()
                fetchAllCountriesLiveData.postError(it, "Error Fetching Country List!")
            }
        }
    }

    fun doGetCountries() {
        getCountriesLiveData.postLoading()
        viewModelScope.launch {
            val result = kotlin.runCatching {
                getCountries.execute(Unit)
                    .flowOn(Dispatchers.IO)
                    .collect {
                        getCountriesLiveData.postSuccess(it)
                    }
            }

            result.exceptionOrNull()?.let {
                it.printStackTrace()
                getCountriesLiveData.postError(it, "Error Fetching Country List!")
            }
        }
    }
}