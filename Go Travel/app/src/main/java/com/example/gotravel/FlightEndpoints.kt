package com.example.gotravel

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface FlightEndpoints {
    @GET("flights")
    fun getProducts(@Header("X-RapidAPI-Key") key : String,
                    /*@Header("X-RapidAPI-Host") host : String,*/
                    @Query("origin_code") origin_code : String,
                    @Query("destination_code") destination_code : String,
                    @Query("origin_departure_date") origin_departure_date : String):
            Call<ArrayList<Flight>>
}