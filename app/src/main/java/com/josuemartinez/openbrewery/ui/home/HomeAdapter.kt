package com.josuemartinez.openbrewery.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.josuemartinez.openbrewery.data.database.DatabaseBrewery
import com.josuemartinez.openbrewery.data.models.Brewery
import com.josuemartinez.openbrewery.databinding.ListItemBreweryBinding


class HomeAdapter(private val clickListener: BreweryListener): ListAdapter<DatabaseBrewery,
        TextItemViewHolder>(BreweryDiffCallback()) {

    var breweries = listOf<DatabaseBrewery>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            // Notify any registered observers that the data set has changed. This will cause every
            // element in our RecyclerView to be invalidated.
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = breweries.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val binding = ListItemBreweryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return TextItemViewHolder(binding)
    }
    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.brewery = breweries[position]
            //it.clickListener = clickListener
        }
    }






}
/**
 * A ViewHolder holds a view for the [RecyclerView] as well as providing additional information
 * to the RecyclerView such as where on the screen it was last drawn during scrolling.
 */
class TextItemViewHolder(val viewDataBinding: ListItemBreweryBinding): RecyclerView.ViewHolder(viewDataBinding.root)

// Create BreweryDiffCallback
class BreweryDiffCallback() : DiffUtil.ItemCallback<DatabaseBrewery>() {
    override fun areItemsTheSame(oldItem: DatabaseBrewery, newItem: DatabaseBrewery): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DatabaseBrewery, newItem: DatabaseBrewery): Boolean {
        return oldItem == newItem
    }

}

class BreweryListener(val block: (DatabaseBrewery) -> Unit) {
    fun onClick(brewery: DatabaseBrewery) = block(brewery)
}




