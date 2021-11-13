package com.example.listsui.mvvm.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.animalsounds.R
import com.example.animalsounds.databinding.FragmentAnimalBinding
import com.example.animalsounds.mvvm.model.Animal

class AnimalFragment(private val animal: Animal) : Fragment() {

    private lateinit var binding: FragmentAnimalBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_animal, container, false)
        binding.animal = animal
        binding.buttonBack.setOnClickListener { activity?.onBackPressed() }

        animal.animalText

        Glide
            .with(binding.root)
            .load(animal.animalAvatar)
            .placeholder(R.drawable.button_icon_play)
            .into(binding.animalPicFragment)
        return binding.root
    }
}