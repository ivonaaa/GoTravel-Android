package com.example.gotravel

data class ApiResponse(
    var origin_to_destination_trip : ArrayList<ArrayList<Flight>>,
    var destination_to_origin_trip : ArrayList<ArrayList<Flight>>
)
