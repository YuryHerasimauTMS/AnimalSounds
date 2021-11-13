package com.example.animalsounds.mvvm.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.animalsounds.databinding.ActivityFlowBinding
import com.example.animalsounds.mvvm.model.Animal
import com.example.animalsounds.mvvm.model.AnimalsGenerator
import com.example.listsui.mvvm.view.AnimalFragment
import com.example.listsui.mvvm.view.ListAnimalsFragment
import java.io.InputStream


class FlowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFlowBinding

    private val beastsPath: String = "animalNames/beastsNames"
    private val birdsPath: String = "animalNames/birdsNames"
    private val reptilesPath: String = "animalNames/reptilesNames"
    private val waterfowlsPath: String = "animalNames/waterfowlsNames"

    private val beastsTextPath: String = "animalText/beastsText.txt"
    private val birdsTextPath: String = "animalText/birdsText.txt"
    private val reptilesTextPath: String = "animalText/reptilesText.txt"
    private val waterfowlsTextPath: String = "animalText/waterfowlsText.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideSystemUI()

        readAllAnimals(beastsPath, beastsTextPath)
        readAllAnimals(birdsPath, birdsTextPath)
        readAllAnimals(reptilesPath, reptilesTextPath)
        readAllAnimals(waterfowlsPath, waterfowlsTextPath)

        setListAnimalsFragment()
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
        supportActionBar?.hide()
        window.decorView.setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                    or View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                    or View.SYSTEM_UI_FLAG_IMMERSIVE
        )
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

    private fun readAllAnimals(path: String, pathText: String) {
        var animalsId: String? = null
        when (path) {
            beastsPath -> animalsId = "beasts"
            birdsPath -> animalsId = "birds"
            reptilesPath -> animalsId = "reptiles"
            waterfowlsPath -> animalsId = "waterfowls"
        }
        val animalList = mutableListOf<String>()
        val animalTextList = mutableListOf<String>()
        var inputStream: InputStream = assets.open(path)
        inputStream.bufferedReader().forEachLine { animalList.add(it) }
        inputStream.close()
        inputStream = assets.open(pathText)
        inputStream.bufferedReader().forEachLine { animalTextList.add(it) }
        AnimalsGenerator.setAnimalsList(animalList, animalsId, animalTextList)
        AnimalsGenerator.compareTextAnimalsToAnimals(animalsId)
    }
}