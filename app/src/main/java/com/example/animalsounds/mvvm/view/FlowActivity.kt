package com.example.animalsounds.mvvm.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.animalsounds.databinding.ActivityFlowBinding
import com.example.animalsounds.mvvm.model.Animal
import com.example.listsui.mvvm.view.AnimalFragment
import com.example.listsui.mvvm.view.ListAnimalsFragment

class FlowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFlowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListAnimalsFragment()
    }

    private fun setListAnimalsFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.container.id, ListAnimalsFragment { showAnimalInfo(it) })
            .commit()
    }

    private fun showAnimalInfo(animal: Animal) {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.container.id, AnimalFragment(animal))
            .addToBackStack(null)
            .commit()
    }
}