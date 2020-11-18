package com.neo.androidanimations

import android.content.Intent
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.View
import android.view.animation.AnticipateOvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import kotlinx.android.synthetic.main.activity_constraint_layout_transition.*


/**
 * activity used to implement keyframe or constraint layout anim
 * and anim is applied to only immediate children of this constraint layout
 */
class ConstraintLayoutTransitionActivity : AppCompatActivity() {

    // true only when the current layout is swapped with the detailed version of it
    private var isDetailLayout = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_layout_transition)

        constraintLayout.setOnClickListener {
            if (isDetailLayout)
                // switch to default layout
                swapFrames(R.layout.activity_constraint_layout_transition)
            else{
                // switch to detail layout
                swapFrames(R.layout.activity_constraint_layout_transition_detail)
            }
        }
    }

    private fun swapFrames(layoutId: Int){
        val constraintSet = ConstraintSet()

        // gets all child views and constraints of the new layout passed to replaces the old one
        constraintSet.clone(this, layoutId)

        // transition obj
        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 1000

        // call on root layout to apply transitions
        TransitionManager.beginDelayedTransition(constraintLayout, transition)
        constraintSet.applyTo(constraintLayout)

        isDetailLayout = !isDetailLayout
    }

    fun goToRvAnimActivity(view: View) {
        startActivity(Intent(this, RvAnimActivity::class.java))
    }
}
