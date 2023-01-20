package com.example.gotravel

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class flightFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_flight, container, false)

        view.findViewById<Button>(R.id.homeButtonFlights)
            .setOnClickListener {
                Navigation.findNavController(view)
                    .navigate(R.id.action_flightFragment_to_main_fragment)
            }

        val request = FlightServiceBulider.buildService(FlightEndpoints::class.java)

        view.findViewById<Button>(R.id.searchButton).setOnClickListener {
            val call = request.getProducts("de8a923b62msh15b6446eff599d6p191858jsna56ac4e5e24b",
                /*"ryanair.p.rapidapi.com",*/
                view.findViewById<EditText>(R.id.originCodeText).text.toString(),
                view.findViewById<EditText>(R.id.destinationCodeText).text.toString(),
                view.findViewById<EditText>(R.id.departureDateText).text.toString())

            call.enqueue(object : Callback<ApiResponse> {
                override fun onResponse(
                    call: Call<ApiResponse>,
                    response: Response<ApiResponse>
                ) {
                    if (response.isSuccessful) {
                        view.findViewById<RecyclerView>(R.id.flightsRecyclerView).apply {
                            layoutManager =
                                LinearLayoutManager(activity)
                            adapter =
                                FlightRecyclerAdapter(response.body()!!.origin_to_destination_trip[0])
                        }
                    }
                }
                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Log.e("FAIL", t.message.toString())
                }
            })
        }
        return view
    }

}