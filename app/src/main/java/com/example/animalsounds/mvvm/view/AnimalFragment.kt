package com.example.animalsounds.mvvm.view

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.animalsounds.R
import com.example.animalsounds.databinding.FragmentAnimalBinding
import com.example.animalsounds.mvvm.model.Animal

class AnimalFragment(private val animal: Animal) : Fragment() {

    private lateinit var binding: FragmentAnimalBinding
    private var playButtonStatus: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val picId = context?.resIdByName(animal.animalAvatar, "drawable")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_animal, container, false)
        binding.animal = animal
        buttonBackOnClick()
        buttonPlayOnClick()
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

    private fun buttonBackOnClick() {
        binding.buttonBack.setOnClickListener {
            it.setBackgroundColor(R.color.pine_green.dec())

            Handler().postDelayed({
                activity?.onBackPressed()
            }, 170)
        }
    }

    private fun buttonPlayOnClick() {
        binding.buttonPlay.setOnClickListener {
            when (playButtonStatus) {
                0 -> {
                    val buttonId = context?.resIdByName("button_play_stop", "drawable")
                    if (buttonId != null) {
                        it.setBackgroundResource(buttonId)
                    }
                    playButtonStatus = 1
                }
                1 -> {
                    val buttonId = context?.resIdByName("button_play_play", "drawable")
                    if (buttonId != null) {
                        it.setBackgroundResource(buttonId)
                    }
                    playButtonStatus = 0
                }
            }


        }
    }
}