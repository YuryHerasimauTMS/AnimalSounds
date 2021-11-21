package com.example.animalsounds

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.animalsounds.mvvm.model.AnimalsGenerator
import com.example.animalsounds.mvvm.view.FlowActivity
import java.io.InputStream

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    private val beastsPath: String = "animalNames/beastsNames"
    private val birdsPath: String = "animalNames/birdsNames"
    private val reptilesPath: String = "animalNames/reptilesNames"
    private val waterfowlsPath: String = "animalNames/waterfowlsNames"

    private val beastsTextPath: String = "animalText/beastsText.txt"
    private val birdsTextPath: String = "animalText/birdsText.txt"
    private val reptilesTextPath: String = "animalText/reptilesText.txt"
    private val waterfowlsTextPath: String = "animalText/waterfowlsText.txt"

    private var flag: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        hideSystemUI()

        if (flag == 0) {
            readAllAnimals(beastsPath, beastsTextPath)
            readAllAnimals(birdsPath, birdsTextPath)
            readAllAnimals(reptilesPath, reptilesTextPath)
            readAllAnimals(waterfowlsPath, waterfowlsTextPath)
        }
        flag = 1

        Handler().postDelayed({
            val intent = Intent(this, FlowActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)

        startAnimation()
    }

    private fun startAnimation() {
        val waveOne = findViewById<ImageView>(R.id.wave_one)
        val waveTwo = findViewById<ImageView>(R.id.wave_two)
        val waveThird = findViewById<ImageView>(R.id.wave_three)
        val waveFourth = findViewById<ImageView>(R.id.wave_four)
        val waveFifth = findViewById<ImageView>(R.id.wave_fifth)
        val logo = findViewById<ImageView>(R.id.logo)

        startWaves(waveOne, 1000f, -1000f)
        startWaves(waveTwo, -1000f, 1000f)
        startWaves(waveThird, 1000f, -1000f)
        startWaves(waveFourth, -1000f, 1000f)
        startWaves(waveFifth, 1000f, -1000f)

        startLogo(logo)
    }

    private fun startLogo(imageView: ImageView) {
        val animator = ObjectAnimator.ofFloat(imageView, View.ALPHA, 0f, 1f)
        animator.duration = 1200
        animator.repeatCount = 2
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.start()
    }

    private fun startWaves(imageView: ImageView, firstValue: Float, secondValue: Float) {
        val animator = ObjectAnimator.ofFloat(imageView, View.X, firstValue, secondValue)
        animator.duration = 4000
        animator.start()
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