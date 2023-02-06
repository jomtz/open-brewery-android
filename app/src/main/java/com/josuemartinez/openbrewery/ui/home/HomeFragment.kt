package com.josuemartinez.openbrewery.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.josuemartinez.openbrewery.R
import com.josuemartinez.openbrewery.databinding.FragmentHomeBinding


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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false)
        Log.i("Home Fragment", "onCreateView")

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        //Populate recycler adapters
        homeAdapter = HomeAdapter(BreweryListener {
            Log.i("Home Fragment", "Brewery Listener")
            findNavController()
                .navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailsFragment()
                )
        })


        viewModel.breweryList.observe(viewLifecycleOwner, Observer { breweries ->
            breweries.apply {
                homeAdapter.breweries = breweries
            }
        })

        binding.root.findViewById<RecyclerView>(R.id.brewery_list_recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapter
        }

        binding.breweryListRecyclerView.adapter = homeAdapter





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









