package com.example.animalsounds

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.provider.ContactsContract
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.animalsounds.mvvm.view.FlowActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        hideSystemUI()

        Handler().postDelayed({
            val intent = Intent(this, FlowActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

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

        startLogo(logo, 0f, 1f)
    }

    private fun startLogo(imageView: ImageView, firstValue: Float, secondValue: Float) {
        val animator = ObjectAnimator.ofFloat(imageView, View.ALPHA, firstValue, secondValue)
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
}