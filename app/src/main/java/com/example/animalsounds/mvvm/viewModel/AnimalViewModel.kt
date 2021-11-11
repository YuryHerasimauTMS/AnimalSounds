package com.example.animalsounds.mvvm.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animalsounds.mvvm.model.Animal
import com.example.animalsounds.mvvm.model.AnimalsGenerator

class AnimalViewModel : ViewModel() {

    private val animalsListLiveData = MutableLiveData<List<Animal>>()

    init {
        val list = AnimalsGenerator.createAnimalList("beast")
        animalsListLiveData.postValue(list)
    }
}