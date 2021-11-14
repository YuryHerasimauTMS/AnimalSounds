package com.example.animalsounds.mvvm.view

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val picId = context?.resIdByName(animal.animalAvatar, "drawable")
        if (picId != null) {
            Glide
                .with(binding.root)
                .load(animal.animalAvatar)
                .placeholder(picId)
                .into(binding.animalPicFragment)
            return binding.root
        }
        return null
    }

    private fun Context.resIdByName(resIdName: String?, resType: String): Int {
        resIdName?.let {
            return resources.getIdentifier(it, resType, packageName)
        }
        throw Resources.NotFoundException()
    }
}