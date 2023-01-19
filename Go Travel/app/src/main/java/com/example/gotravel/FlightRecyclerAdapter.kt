package com.example.gotravel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FlightRecyclerAdapter(private val items: List<Flight>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FlightViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.flight_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is FlightViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class FlightViewHolder constructor(
        itemView : View
    ) : RecyclerView.ViewHolder(itemView){
        private val  originCode : TextView =
            itemView.findViewById(R.id.originCodeTextView)
        private val  destinationCode : TextView =
            itemView.findViewById(R.id.destinationCodeTextView)
        private val  departureDatetime : TextView =
            itemView.findViewById(R.id.departureDateTimeTextView)
        private val  arrivalDatetime : TextView =
            itemView.findViewById(R.id.arrivalDateTimeTextView)
        private val  flightNumber : TextView =
            itemView.findViewById(R.id.flightNumberTextView)

        fun bind(flights : Flight){
            originCode.text = flights.origin_code
            destinationCode.text = flights.destination_code
            departureDatetime.text = flights.departure_datetime
            arrivalDatetime.text = flights.arrival_datetime
            flightNumber.text = flights.flight_number
        }
    }
}