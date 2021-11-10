package com.example.animalsounds.mvvm.model

import java.io.File
import java.io.InputStream

class AnimalsGenerator {

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
        val inputStream: InputStream = File(path).inputStream()
        inputStream.bufferedReader().forEachLine { lineList.add(it) }
        lineList.forEach {
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
        return null
    }
}