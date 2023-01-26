package com.josuemartinez.openbrewery.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.josuemartinez.openbrewery.R
import com.josuemartinez.openbrewery.data.models.Brewery
import com.josuemartinez.openbrewery.databinding.FragmentHomeBinding
import com.josuemartinez.openbrewery.databinding.ListItemBreweryBinding


class HomeFragment : Fragment() {


    private val viewModel: HomeViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, HomeViewModel.HomeViewModelFactory(activity.application))
            .get(HomeViewModel::class.java)

    }



    /**
     * RecyclerView Adapter for converting a list of Brewery to cards.
     */
    //private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: HomeAdapter? = null

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        viewModel.breweryList.observe(viewLifecycleOwner, Observer<List<Brewery>> {breweries ->
            breweries?.apply {
                adapter?.breweries = breweries
            }
        })

    }
    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        adapter = HomeAdapter(BreweryClick {
            // When a brewery is clicked this block or lambda will be called by HomeAdapter
            // context is not around, we can safely discard this click since the Fragment is no
            // longer on the screen
            val packageManager = context?.packageManager ?: return@BreweryClick

            // Try to generate a direct intent to the YouTube app
            var intent = Intent(Intent.ACTION_VIEW, it.launchUri)
            if(intent.resolveActivity(packageManager) == null) {
                // YouTube app isn't found, use the web url
                intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.websiteUrl))
            }

            startActivity(intent)
        })

        binding.root.findViewById<RecyclerView>(R.id.brewery_list).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapter
        }

        // Observer for the network error.
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) {
                onNetworkError()
            }
        })


        return binding.root
    }


    /**
     * Method for displaying a Toast error message for network errors.
     */
    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

    /**
     * Helper method to generate YouTube app links
     */
    private val Brewery.launchUri: Uri
        get() {
            val httpUri = Uri.parse(websiteUrl)
            return Uri.parse("vnd.youtube:" + httpUri.getQueryParameter("v"))
        }





}

/**
 * Click listener for Breweries. By giving the block a name it helps a reader understand what it does.
 *
 */
class BreweryClick(val block: (Brewery) -> Unit) {
    /**
     * Called when a brewery is clicked
     *
     * @param brewery the video that was clicked
     */
    fun onClick(brewery: Brewery) = block(brewery)
}



class HomeAdapter(private val callback: BreweryClick) : RecyclerView.Adapter<HomeViewHolder>() {



    /**
     * The breweries that our Adapter will show
     */
    var breweries: List<Brewery> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            // Notify any registered observers that the data set has changed. This will cause every
            // element in our RecyclerView to be invalidated.
            notifyDataSetChanged()
        }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val withDataBinding: ListItemBreweryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            HomeViewHolder.LAYOUT,
            parent,
            false)
        return HomeViewHolder(withDataBinding)
    }

    override fun getItemCount() = breweries.size

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     */
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.brewery = breweries[position]
            it.breweryCallback = callback
        }
    }



}
/**
 * ViewHolder for Brewery items. All work is done by data binding.
 */
class HomeViewHolder(val viewDataBinding: ListItemBreweryBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.list_item_brewery
    }
}





