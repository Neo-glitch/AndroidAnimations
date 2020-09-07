package com.neo.androidanimations

import android.content.Intent
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import kotlinx.android.synthetic.main.activity_vector_drawable_anim.*

class VectorDrawableAnimActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vector_drawable_anim)

        var isChecked = true

        avdImage.setOnClickListener {
            if (isChecked) {  // check icon is showing
                checkToClose()
            } else{
                closeToCheck()
            }
            isChecked = !isChecked
        }
    }

    /**
     * fun to implement vector anim from close to eheck icon
     */
    private fun closeToCheck() {
        avdImage.setImageResource(R.drawable.avd_close_to_check)
        val avdCloseToCheck = avdImage.drawable as AnimatedVectorDrawable
        avdCloseToCheck.start()
    }

    /**
     * function to implement vector animation
     */
    private fun checkToClose() {
        avdImage.setImageResource(R.drawable.avd_check_to_close)
        val avdCheckToClose = avdImage.drawable as AnimatedVectorDrawable
        avdCheckToClose.start()

    }

    fun toTransitionActivity(view: View) {
        startActivity(Intent(this, TransitionActivity::class.java))
    }
}