package com.example.animalsounds.mvvm.model

import android.content.Context
import android.util.Log
import com.example.animalsounds.mvvm.viewModel.AnimalViewModel
import java.io.File
import java.io.FileReader
import java.io.InputStream
import java.security.AccessController.getContext

object AnimalsGenerator {

    private val lineList = mutableListOf<String>()
    private val beastList = mutableListOf<Animal>()
    private val birdsList = mutableListOf<Animal>()
    private val reptilesList = mutableListOf<Animal>()
    private val waterfowlList = mutableListOf<Animal>()

    private val beastsPath = "beastsNames.txt"
    private val birdsPath = "birdsNames.txt"
    private val reptilesPath = "reptilesNames.txt"
    private val waterfowlsPath = "waterfowlsNames.txt"

    private fun createListOfStrings(path: String): MutableList<Animal>? {
        Log.d("AnimalsGeneratorR", "AnimalsGeneratorR")
        val file = FileReader("beastsNames")
        file.read()
        Log.d("AnimalsGeneratorRR", "AnimalsGeneratorRR")
        val inputStream: InputStream = File("animalNames/beastsNames.txt").inputStream()
        inputStream.bufferedReader().forEachLine { lineList.add(it) }
        lineList.forEach {
            println(it)
            // Здесь три слова (имя, аватар, звук, текст)
            val words = it.split("\\s+".toRegex()).map { word ->
                word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
            }
            // Создаем экземпляр животного
            val animal = Animal(words[0], words[1], words[2], words[3])
            // Запись в соответствующий лист
            when (path) {
                beastsPath -> beastList.add(animal)
                birdsPath -> birdsList.add(animal)
                reptilesPath -> reptilesList.add(animal)
                waterfowlsPath -> waterfowlList.add(animal)
            }
        }
        Log.d("AnimalsGeneratorRRR", "AnimalsGeneratorRRR")
        when (path) {
            beastsPath -> return beastList
            birdsPath -> return birdsList
            reptilesPath -> return reptilesList
            waterfowlsPath -> return waterfowlList
        }
        return null
    }

    fun createAnimalList(animalType: String): MutableList<Animal>? {
        when (animalType) {
            "beasts" -> return createListOfStrings(beastsPath)
            "birds" -> return createListOfStrings(birdsPath)
            "reptiles" -> return createListOfStrings(reptilesPath)
            "waterfowls" -> return createListOfStrings(waterfowlsPath)
        }
//        val animalOne = Animal("nameA", "avatarA", "soundA", "textA")
//        val animalTwo = Animal("nameB", "avatarB", "soundB", "textB")
//        val animalList = mutableListOf<Animal>()
//        animalList.add(animalOne)
//        animalList.add(animalTwo)
//        return animalList
        return null
    }
}