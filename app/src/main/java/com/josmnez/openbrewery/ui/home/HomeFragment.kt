package com.josmnez.openbrewery.ui.home

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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.josmnez.openbrewery.R
import com.josmnez.openbrewery.data.models.Brewery
import com.josmnez.openbrewery.databinding.FragmentHomeBinding
import com.josmnez.openbrewery.databinding.ListItemBreweryBinding


class HomeFragment : Fragment() {


    private val viewModel: HomeViewModel by lazy {
        val activity = requireNotNull(this.activity).application
        val viewModelFactory = HomeViewModel.HomeViewModelFactory(activity)
        ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

    }

    /**
     * RecyclerView Adapter for converting a list of Brewery to cards.
     */
    private lateinit var homeAdapter: HomeAdapter

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        viewModel.breweryList.observe(viewLifecycleOwner) { breweries ->
            breweries.apply {
                homeAdapter.breweries = breweries
            }
        }

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

        //Populate recycler adapters
        homeAdapter = HomeAdapter(BreweryListener {
//            findNavController().navigate(
//
//            )
        })
        binding.breweryListRecyclerView.adapter = homeAdapter

        viewModel.breweryList.observe(viewLifecycleOwner, Observer { breweries ->
            breweries.apply {
                homeAdapter.breweries = breweries
            }
        })




        binding.root.findViewById<RecyclerView>(R.id.brewery_list_recycler_view).apply {
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






}









