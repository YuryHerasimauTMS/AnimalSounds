package com.example.animalsounds.mvvm.view

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.animalsounds.R
import com.example.animalsounds.databinding.ActivityFlowBinding
import com.example.animalsounds.mvvm.model.Animal


class FlowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFlowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideSystemUI()
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
        startLeavesAnimation()
        Handler().postDelayed({
            supportFragmentManager
                .beginTransaction()
                .replace(binding.container.id, AnimalFragment(animal))
                .addToBackStack(null)
                .commit()
        }, 300)
    }

    private fun startLeavesAnimation() {
        val leafLeftBottom = findViewById<ImageView>(R.id.leafLeftBottom)
        val leafLeftUpper = findViewById<ImageView>(R.id.leafLeftUpper)
        val leafRightBottom = findViewById<ImageView>(R.id.leafRightBottom)
        val leafRightUpper = findViewById<ImageView>(R.id.leafRightUpper)

        animateLeaves(leafLeftBottom, 0f, -300F)
        animateLeaves(leafLeftUpper, -380F, -600F)
        animateLeaves(leafRightBottom, 140F, 600F)
        animateLeaves(leafRightUpper, 200F, 600F)
    }

    private fun animateLeaves(
        leaf: ImageView,
        startXPosition: Float,
        endXPosition: Float
    ) {
        leaf.x = startXPosition
        leaf.animate()
            .x(endXPosition)
            .setStartDelay(0)
            .rotation(-90f)
            .setDuration(300).interpolator = AccelerateDecelerateInterpolator()
    }
}