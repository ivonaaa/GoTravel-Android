package com.example.gotravel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


enum class ItemClickType {
    REMOVE,
}

class TripRecyclerAdapter(
    val items: ArrayList<Trip>,
    val listener: ContentListener
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder {
        return TripViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.trip_layout,
                parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TripViewHolder -> {
                holder.bind(position, listener, items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun removeItem(index: Int) {
        items.removeAt(index)
        notifyItemRemoved(index)
        notifyItemRangeChanged(index, items.size)
    }

    fun addItem(person : Trip){
        items.add(person)
        notifyItemInserted(items.size)
    }

    class TripViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        private val tripName =
            view.findViewById<TextView>(R.id.tripNameTextView)
        private val tripLocation =
            view.findViewById<TextView>(R.id.tripLocatonTextView)
        private val tripDate =
            view.findViewById<TextView>(R.id.tripDateTextView)
        private val tripInfo =
            view.findViewById<TextView>(R.id.tripInfoTextView)
        private val deleteBtn =
            view.findViewById<ImageButton>(R.id.tripDeleteButton)
        fun bind(
            index: Int,
            listener: ContentListener,
            trip: Trip
        ) {

            tripName.text = trip.name
            tripLocation.text = trip.location
            tripDate.text = trip.date
            tripInfo.text = trip.info

            deleteBtn.setOnClickListener {
                listener.onItemButtonClick(index, trip,
                    ItemClickType.REMOVE)
            }
        }
    }

    interface ContentListener {
        fun onItemButtonClick(index: Int, trip: Trip, clickType:
        ItemClickType)
    }
}