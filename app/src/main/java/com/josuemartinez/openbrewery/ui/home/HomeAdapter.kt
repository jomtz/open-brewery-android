package com.josuemartinez.openbrewery.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.josuemartinez.openbrewery.R
import com.josuemartinez.openbrewery.data.models.Brewery
import com.josuemartinez.openbrewery.ui.details.DetailsFragment

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {


    private val name = arrayOf(
        "10-56 Brewing Company",
        "10 Barrel Brewing Co",
        "10 Barrel Brewing Co - Bend Pub",
        "10 Barrel Brewing Co - Boise",
        "10 Barrel Brewing Co - Denver",
        "10 Barrel Brewing Co",
        "10 Barrel Brewing Co",
        "10 Torr Distilling and Brewing",
        "10-56 Brewing Company",
        "10 Barrel Brewing Co",
        "10 Barrel Brewing Co - Bend Pub",
        "10 Barrel Brewing Co - Boise",
        "10 Barrel Brewing Co - Denver",
        "10 Barrel Brewing Co",
        "10 Barrel Brewing Co",
        "10 Torr Distilling and Brewing"
    )

    private val city = arrayOf(
        "Knox",
        "Bend",
        "Bend",
        "Boise",
        "Denver",
        "Portland",
        "San Diego",
        "Reno",
        "Knox",
        "Bend",
        "Bend",
        "Boise",
        "Denver",
        "Portland",
        "San Diego",
        "Reno"
    )

    private val state = arrayOf(
        "Indiana",
        "Oregon",
        "Oregon",
        "Idaho",
        "Colorado",
        "Oregon",
        "California",
        "Nevada",
        "Indiana",
        "Oregon",
        "Oregon",
        "Idaho",
        "Colorado",
        "Oregon",
        "California",
        "Nevada"
    )

    //var data = listOf<Brewery>()










    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item_brewery, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.nameTextView.text = name[i]
        viewHolder.cityTextView.text = city[i]
        viewHolder.stateTextView.text = state[i]

    }

    override fun getItemCount(): Int {
        return name.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var nameTextView: TextView
        var cityTextView: TextView
        var stateTextView: TextView

        init {
            nameTextView = itemView.findViewById(R.id.name_text)
            cityTextView = itemView.findViewById(R.id.city_text)
            stateTextView = itemView.findViewById(R.id.state_text)

            itemView.setOnClickListener {
                val position: Int = adapterPosition
                val context = itemView.context
                val intent = Intent(context, DetailsFragment::class.java).apply {
                    putExtra("NUMBER", position)
                    putExtra("NAME", name)
                    putExtra("CITY", city)
                    putExtra("STATE", state)
                }
                context.startActivity(intent)
            }
        }
    }

}




