package com.example.gotravel

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FlightServiceBulider {
    private val client = OkHttpClient.Builder().build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://ryanair.p.rapidapi.com")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}