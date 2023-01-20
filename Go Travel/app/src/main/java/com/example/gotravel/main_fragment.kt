package com.example.gotravel

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class main_fragment : Fragment(),
TripRecyclerAdapter.ContentListener{

    private val db = Firebase.firestore
    private lateinit var recyclerAdapter: TripRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val tripsRecyclerView = view.findViewById<RecyclerView>(R.id.tripsRecyclerView)
        db.collection("trips")
            .get()
            .addOnSuccessListener {
                val tripsList = ArrayList<Trip>()
                for (data in it.documents) {
                    val trip = data.toObject(Trip::class.java)
                    if (trip != null) {
                        trip.id = data.id
                        tripsList.add(trip)
                    }
                }
                recyclerAdapter = TripRecyclerAdapter(tripsList,
                    this@main_fragment)
                tripsRecyclerView.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = recyclerAdapter
                    addItemDecoration(DividerItemDecoration(
                        this.context, DividerItemDecoration.VERTICAL))
                }
            }
            .addOnFailureListener { exception ->
                Log.w("MainActivity", "Error getting documents.",
                    exception)
            }


        view.findViewById<Button>(R.id.newTripButton)
            .setOnClickListener{ Navigation.findNavController(view).navigate(R.id.action_main_fragment_to_newTripFragment) }

        view.findViewById<Button>(R.id.flightButton)
            .setOnClickListener{ Navigation.findNavController(view).navigate(R.id.action_main_fragment_to_flightFragment) }

        view.findViewById<Button>(R.id.guideButton)
            .setOnClickListener{ Navigation.findNavController(view).navigate(R.id.action_main_fragment_to_guideFragment) }

        return view
    }

    override fun onItemButtonClick(index: Int, trip: Trip, clickType: ItemClickType) {
        if (clickType == ItemClickType.REMOVE) {
            recyclerAdapter.removeItem(index)
            db.collection("trips")
                .document(trip.id)
                .delete()
        }
    }

}