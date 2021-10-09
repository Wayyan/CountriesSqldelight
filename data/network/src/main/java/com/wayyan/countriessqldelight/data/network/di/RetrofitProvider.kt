package com.wayyan.countriessqldelight.data.network.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.wayyan.countriessqldelight.domain.model.K
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitProvider {
    private var retrofit: Retrofit? = null

    fun retrofit(context: Context): Retrofit {
        if (retrofit != null)
            return retrofit!!

//        val moshi = Moshi.Builder()
//            .build()
//
//        val adapter = moshi.adapter(CountryResponse::class.java)

        return Retrofit.Builder().baseUrl(K.baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpProvider.okHttpClient(context))
            .build()
    }
}