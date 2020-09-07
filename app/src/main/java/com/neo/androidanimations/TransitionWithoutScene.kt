package com.neo.androidanimations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Fade
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import kotlinx.android.synthetic.main.activity_transition_without_scene.*


/**
 * implements transition without the need of different scene or layout files
 */
class TransitionWithoutScene : AppCompatActivity() {
    private var visibility = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition_without_scene)

    }

    fun fadeAnimation(view: View) {
        val transition = Fade()                 // fade transition

        // sceneRoot is parent layout id
        TransitionManager.beginDelayedTransition(sceneRoot, transition)

        // toggle visibility of targetView, if view is visible make it invisible else make it visible
        // when we try to modify prop of View SceneRoot by hiding or showing tv, transition is activated
        txvDescription.visibility = if (visibility) View.INVISIBLE else View.VISIBLE
        visibility = !visibility
    }

    fun slideEffect(view: View) {

        val transition = Slide(Gravity.START)
        TransitionManager.beginDelayedTransition(sceneRoot, transition)

        // toggle visibility of targetView, if view is visible make it invisible else make it visible
        // when we try to modify prop of View SceneRoot by hiding or showing tv, transition is activated
        txvDescription.visibility = if (visibility) View.INVISIBLE else View.VISIBLE
        visibility = !visibility
    }
}
