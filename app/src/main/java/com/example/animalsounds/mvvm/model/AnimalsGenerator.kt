package com.example.animalsounds.mvvm.model

object AnimalsGenerator {

    var beastsList = mutableListOf<Animal>()
    var birdsList = mutableListOf<Animal>()
    var reptilesList = mutableListOf<Animal>()
    var waterfowlsList = mutableListOf<Animal>()

    private val beastsTextList = mutableListOf<String>()
    private val birdsTextList = mutableListOf<String>()
    private val reptilesTextList = mutableListOf<String>()
    private val waterfowlsTextList = mutableListOf<String>()

    val beastsPicIdList = mutableMapOf<Animal, Int>()
    val birdsPicIdList = mutableMapOf<Animal, Int>()
    val reptilesPicIdList = mutableMapOf<Animal, Int>()
    val waterfowlsPicIdList = mutableMapOf<Animal, Int>()

    private const val beasts = "beasts"
    private const val birds = "birds"
    private const val reptiles = "reptiles"
    private const val waterfowls = "waterfowls"

    fun createAnimalList(animalType: String): MutableList<Animal>? {
        when (animalType) {
            beasts -> return beastsList
            birds -> return birdsList
            reptiles -> return reptilesList
            waterfowls -> return waterfowlsList
        }
        return null
    }

    fun setAnimalsList(list: List<String>, id: String?, textList: List<String>) {
        when (id) {
            beasts -> {
                list.forEach {
                    println(it)
                    val words = it.split("\\s+".toRegex()).map { word ->
                        word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                    }
                    val animal = Animal(words[0], words[1], words[2], words[3])
                    beastsList.add(animal)
                }
                textList.forEach {
                    beastsTextList.add(it)
                }
            }
            birds -> {
                list.forEach {
                    println(it)
                    val words = it.split("\\s+".toRegex()).map { word ->
                        word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                    }
                    val animal = Animal(words[0], words[1], words[2], words[3])
                    birdsList.add(animal)
                }
                textList.forEach {
                    birdsTextList.add(it)
                }
            }
            reptiles -> {
                list.forEach {
                    println(it)
                    val words = it.split("\\s+".toRegex()).map { word ->
                        word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                    }
                    val animal = Animal(words[0], words[1], words[2], words[3])
                    reptilesList.add(animal)
                }
                textList.forEach {
                    reptilesTextList.add(it)
                }
            }
            waterfowls -> {
                list.forEach {
                    println(it)
                    val words = it.split("\\s+".toRegex()).map { word ->
                        word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                    }
                    val animal = Animal(words[0], words[1], words[2], words[3])
                    waterfowlsList.add(animal)
                }
                textList.forEach {
                    waterfowlsTextList.add(it)
                }
            }
        }
    }

    fun getTextAnimalsList(id: String): List<String>? {
        when (id) {
            beasts -> return beastsTextList
            birds -> return birdsTextList
            reptiles -> return reptilesTextList
            waterfowls -> return waterfowlsTextList
        }
        return null
    }

    fun compareTextAnimalsToAnimals(id: String?) {
        val bufferListBeasts = mutableListOf<Animal>()
        val bufferListBirds = mutableListOf<Animal>()
        val bufferListReptiles = mutableListOf<Animal>()
        val bufferListWaterfowls = mutableListOf<Animal>()
        when (id) {
            beasts -> {
                beastsList.forEach { it ->
                    val textId = it.animalText
                    var str = ""
                    var textFlag = 0
                    beastsTextList.forEach {
                        if (textFlag in 1..5) {
                            textFlag++
                        }
                        if (it.startsWith(textId)) {
                            textFlag = 1
                        }
                        if (textFlag in 2..5) {
                            str += it
                            str += "\n"
                        }
                    }
                    it.setText(str)
                    bufferListBeasts.add(it)
                }
                beastsList = bufferListBeasts
            }
            birds -> {
                birdsList.forEach { it ->
                    val textId = it.animalText
                    var str = ""
                    var textFlag = 0
                    birdsTextList.forEach {
                        if (textFlag in 1..5) {
                            textFlag++
                        }
                        if (it.startsWith(textId)) {
                            textFlag = 1
                        }
                        if (textFlag in 2..5) {
                            str += it
                            str += "\n"
                        }
                    }
                    it.setText(str)
                    bufferListBirds.add(it)
                }
                birdsList = bufferListBirds
            }
            reptiles -> {
                reptilesList.forEach { it ->
                    val textId = it.animalText
                    var str = ""
                    var textFlag = 0
                    reptilesTextList.forEach {
                        if (textFlag in 1..5) {
                            textFlag++
                        }
                        if (it.startsWith(textId)) {
                            textFlag = 1
                        }
                        if (textFlag in 2..5) {
                            str += it
                            str += "\n"
                        }
                    }
                    it.setText(str)
                    bufferListReptiles.add(it)
                }
                reptilesList = bufferListReptiles
            }
            waterfowls -> {
                waterfowlsList.forEach { it ->
                    val textId = it.animalText
                    var str = ""
                    var textFlag = 0
                    waterfowlsTextList.forEach {
                        if (textFlag in 1..5) {
                            textFlag++
                        }
                        if (it.startsWith(textId)) {
                            textFlag = 1
                        }
                        if (textFlag in 2..5) {
                            str += it
                            str += "\n"
                        }
                    }
                    it.setText(str)
                    bufferListWaterfowls.add(it)
                }
                waterfowlsList = bufferListWaterfowls
            }
        }
    }
}