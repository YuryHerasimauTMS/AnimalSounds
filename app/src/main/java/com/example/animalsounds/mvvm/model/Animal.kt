package com.example.animalsounds.mvvm.model

data class Animal(
    val animalName: String,
    val animalAvatar: String,
    val animalSound: String,
    var animalText: String
) {
    fun setText(text: String) {
        animalText = text
    }
}