package com.example.listsui.mvvm.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animalsounds.AnimalAdapter
import com.example.animalsounds.R
import com.example.animalsounds.databinding.ActivityMainBinding
import com.example.animalsounds.mvvm.model.Animal
import com.example.animalsounds.mvvm.viewModel.AnimalViewModel

class ListAnimalsFragment(private val showAnimalInfo: (Animal) -> Unit): Fragment() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: AnimalViewModel
    private val animalsAdapter by lazy{ AnimalAdapter(showAnimalInfo) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_main, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(AnimalViewModel::class.java)
        binding.viewModel = viewModel
        setListAdapter()
        setAnimalListener()
        return binding.root
    }

    private fun setListAdapter() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = animalsAdapter
        }
    }

    private fun setAnimalListener(){
        viewModel.animalsListLiveData.observe(viewLifecycleOwner, Observer {
            animalsAdapter.setAnimalsList(it)
        })
    }
}
