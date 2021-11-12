package com.example.animalsounds.mvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animalsounds.mvvm.model.Animal
import com.example.animalsounds.mvvm.model.AnimalsGenerator
import com.example.animalsounds.mvvm.view.FlowActivity

class AnimalViewModel : ViewModel() {

    private val _animalsListLiveData = MutableLiveData<List<Animal>>()
    val animalsListLiveData : LiveData<List<Animal>>
        get() = _animalsListLiveData

    private val animalsList = mutableListOf<Animal>()

    init {
        AnimalsGenerator.createAnimalList("beasts")?.let { animalsList.addAll(it) }
        _animalsListLiveData.postValue(animalsList)
    }
}