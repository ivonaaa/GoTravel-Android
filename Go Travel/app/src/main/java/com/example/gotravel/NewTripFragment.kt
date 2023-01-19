package com.example.gotravel

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.Date

class NewTripFragment : Fragment() {

    private val db = Firebase.firestore
    private lateinit var recyclerAdapter: TripRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_new_trip, container, false)

        val saveBtn = view.findViewById<Button>(R.id.saveButton)
        saveBtn.setOnClickListener {
            val trip = Trip()
            trip.name = view.findViewById<EditText>(R.id.tripNameEditText).text.toString()
            trip.location = view.findViewById<EditText>(R.id.tripLocatoinEditText).text.toString()
            trip.date = view.findViewById<EditText>(R.id.tripDateEditText).text.toString()
            trip.info = view.findViewById<EditText>(R.id.tripInfoEditText).text.toString()
            db.collection("trips")
                .add(trip)
                .addOnSuccessListener { documentReference ->
                    trip.id = documentReference.id
                    //recyclerAdapter.addItem(trip)
                    Toast.makeText(activity, "Trip added!",
                        Toast.LENGTH_LONG).show()
                    Log.d("Fragment new trip", "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("Fragment new trip", "Error adding document", e)
                }
            Navigation.findNavController(view).navigate(R.id.action_newTripFragment_to_main_fragment)
        }

        view.findViewById<Button>(R.id.homeButtonTrip)
            .setOnClickListener{ Navigation.findNavController(view).navigate(R.id.action_newTripFragment_to_main_fragment) }

        return view
    }

}