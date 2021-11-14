package com.example.animalsounds.mvvm.view

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
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
import com.example.animalsounds.mvvm.model.AnimalsGenerator
import com.example.animalsounds.mvvm.model.AnimalsGenerator.beastsPicIdList
import com.example.animalsounds.mvvm.model.AnimalsGenerator.birdsPicIdList
import com.example.animalsounds.mvvm.model.AnimalsGenerator.reptilesPicIdList
import com.example.animalsounds.mvvm.model.AnimalsGenerator.waterfowlsPicIdList
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
        compareAnimalsTextIdToDrawableId()
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
            animalsAdapter.setAnimalsList(it, beastsPicIdList)
        })
    }

    private fun compareAnimalsTextIdToDrawableId() {
        AnimalsGenerator.beastsList.forEach {
            val picId = context?.resIdByName(it.animalAvatar, "drawable")
            if (picId != null) {
                beastsPicIdList[it] = picId
            } else {
                beastsPicIdList[it] = 2131165337
            }
        }
        AnimalsGenerator.birdsList.forEach {
            val picId = context?.resIdByName(it.animalAvatar, "drawable")
            if (picId != null) {
                birdsPicIdList[it] = picId
            } else {
                birdsPicIdList[it] = 2131165337
            }
        }
        AnimalsGenerator.reptilesList.forEach {
            val picId = context?.resIdByName(it.animalAvatar, "drawable")
            if (picId != null) {
                reptilesPicIdList[it] = picId
            } else {
                reptilesPicIdList[it] = 2131165337
            }
        }
        AnimalsGenerator.waterfowlsList.forEach {
            val picId = context?.resIdByName(it.animalAvatar, "drawable")
            if (picId != null) {
                waterfowlsPicIdList[it] = picId
            } else {
                waterfowlsPicIdList[it] = 2131165337
            }
        }
    }

    private fun Context.resIdByName(resIdName: String?, resType: String): Int {
        resIdName?.let {
            return resources.getIdentifier(it, resType, packageName)
        }
        throw Resources.NotFoundException()
    }
}
