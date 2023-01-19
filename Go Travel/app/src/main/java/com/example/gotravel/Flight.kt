package com.example.gotravel

data class Flight(
    var origin_code : String,
    var destination_code : String,
    var departure_datetime : String,
    var arrival_datetime : String,
    var flight_number : String,
)
