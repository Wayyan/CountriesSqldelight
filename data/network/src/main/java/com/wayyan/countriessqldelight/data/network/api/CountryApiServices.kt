package com.wayyan.countriessqldelight.data.network.api

import com.wayyan.countriessqldelight.data.network.api.response.CountryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CountryApiServices {
    @GET("country")
    fun getCountryList(): Call<List<CountryResponse>>
}