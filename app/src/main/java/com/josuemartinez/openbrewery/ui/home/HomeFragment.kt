package com.josuemartinez.openbrewery.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.josuemartinez.openbrewery.R
import com.josuemartinez.openbrewery.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var wordList: MutableList<String>
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )

        breweryList()

        return binding.root
    }

    private fun breweryList() {
        // All the breweries for this List
        wordList = mutableListOf<String>(
            "10-56 Brewing Company",
            "10 Barrel Brewing Co",
            "10 Barrel Brewing Co - Bend Pub",
            "10 Barrel Brewing Co - Boise",
            "10 Barrel Brewing Co - Denver",
            "10 Torr Distilling and Brewing",
            "101 Brewery",
            "101 North Brewing Company",
            "105 West Brewing Co",
            "10K Brewing",
            "10th District Brewing Company",
            "11 Below Brewing Company",
            "1188 Brewing Co",
            "12 Acres Brewing Company",
            "12 Gates Brewing Company",
            "12 West Brewing Company",
            "12 West Brewing Company - Production Facility"
        )
        binding.textview.text = wordList.toString()
    }


}




