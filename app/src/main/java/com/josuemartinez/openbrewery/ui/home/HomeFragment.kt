package com.josuemartinez.openbrewery.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.josuemartinez.openbrewery.R
import com.josuemartinez.openbrewery.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )



        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        listOfWords()

        return binding.root
    }

    private fun listOfWords(){
        binding.textview.text = viewModel.wordList.toString()
    }




}




