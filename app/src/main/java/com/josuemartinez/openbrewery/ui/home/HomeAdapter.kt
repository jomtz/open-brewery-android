package com.josuemartinez.openbrewery.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.josuemartinez.openbrewery.data.models.Brewery

class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data = listOf<Brewery>()



    override fun getItemCount() = data.size




    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }


}




