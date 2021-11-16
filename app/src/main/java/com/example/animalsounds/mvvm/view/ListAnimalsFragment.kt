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
import com.example.animalsounds.mvvm.model.AnimalsGenerator.beastsList
import com.example.animalsounds.mvvm.model.AnimalsGenerator.beastsPicIdList
import com.example.animalsounds.mvvm.model.AnimalsGenerator.birdsList
import com.example.animalsounds.mvvm.model.AnimalsGenerator.birdsPicIdList
import com.example.animalsounds.mvvm.model.AnimalsGenerator.reptilesList
import com.example.animalsounds.mvvm.model.AnimalsGenerator.reptilesPicIdList
import com.example.animalsounds.mvvm.model.AnimalsGenerator.waterfowlsList
import com.example.animalsounds.mvvm.model.AnimalsGenerator.waterfowlsPicIdList
import com.example.animalsounds.mvvm.viewModel.AnimalViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener


class ListAnimalsFragment(private val showAnimalInfo: (Animal) -> Unit) : Fragment() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: AnimalViewModel
    private val animalsAdapter by lazy { AnimalAdapter(showAnimalInfo) }
    private var currentTabSelected: Int = 0
    private var flagOfSetDrawableId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_main, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(AnimalViewModel::class.java)
        binding.viewModel = viewModel
        setListAdapter()
        compareAnimalsTextIdToDrawableId()
        setAnimalListener()
        setTabListener()
        setTabSelected()
        return binding.root
    }

    private fun setTabSelected() {
        when (currentTabSelected) {
            0 -> binding.tab.setScrollPosition(0, 0f, true)
            1 -> binding.tab.setScrollPosition(1, 0f, true)
            2 -> binding.tab.setScrollPosition(2, 0f, true)
            3 -> binding.tab.setScrollPosition(3, 0f, true)
        }
    }

    private fun loadAnimalsByTab(position: Int) {
        when (position) {
            0 -> {
                viewModel.loadAnimalsByTab("birds")
                currentTabSelected = 0
            }
            1 -> {
                viewModel.loadAnimalsByTab("reptiles")
                currentTabSelected = 1
            }
            2 -> {
                viewModel.loadAnimalsByTab("waterfowls")
                currentTabSelected = 2
            }
            3 -> {
                viewModel.loadAnimalsByTab("beasts")
                currentTabSelected = 3
            }
        }
    }

    private fun setTabListener() {
        binding.tab.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                loadAnimalsByTab(tab.position)
                binding.recyclerView.scrollToPosition(0)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                loadAnimalsByTab(tab.position)
                binding.recyclerView.scrollToPosition(0)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                loadAnimalsByTab(tab.position)
                setTabSelected()
                binding.recyclerView.scrollToPosition(0)
            }
        })
    }

    private fun setListAdapter() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = animalsAdapter
        }
    }

    private fun setAnimalListener() {
        viewModel.animalsListLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                beastsList -> animalsAdapter.setAnimalsList(it, beastsPicIdList)
                birdsList -> animalsAdapter.setAnimalsList(it, birdsPicIdList)
                reptilesList -> animalsAdapter.setAnimalsList(it, reptilesPicIdList)
                waterfowlsList -> animalsAdapter.setAnimalsList(it, waterfowlsPicIdList)
            }
        })
    }

    private fun compareAnimalsTextIdToDrawableId() {
        if (flagOfSetDrawableId == 0) {
            beastsList.forEach {
                val picId = context?.resIdByName(it.animalAvatar, "drawable")
                if (picId != null) {
                    beastsPicIdList[it] = picId
                } else {
                    beastsPicIdList[it] = 2131165337
                }
            }
            birdsList.forEach {
                val picId = context?.resIdByName(it.animalAvatar, "drawable")
                if (picId != null) {
                    birdsPicIdList[it] = picId
                } else {
                    birdsPicIdList[it] = 2131165337
                }
            }
            reptilesList.forEach {
                val picId = context?.resIdByName(it.animalAvatar, "drawable")
                if (picId != null) {
                    reptilesPicIdList[it] = picId
                } else {
                    reptilesPicIdList[it] = 2131165337
                }
            }
            waterfowlsList.forEach {
                val picId = context?.resIdByName(it.animalAvatar, "drawable")
                if (picId != null) {
                    waterfowlsPicIdList[it] = picId
                } else {
                    waterfowlsPicIdList[it] = 2131165337
                }
            }
            flagOfSetDrawableId = 1
        }
    }

    private fun Context.resIdByName(resIdName: String?, resType: String): Int {
        resIdName?.let {
            return resources.getIdentifier(it, resType, packageName)
        }
        throw Resources.NotFoundException()
    }
}
