package com.neo.androidanimations

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.*
import android.view.View
import android.view.animation.LinearInterpolator
import kotlinx.android.synthetic.main.activity_transition.*

class TransitionActivity : AppCompatActivity() {

    private lateinit var scene1: Scene
    private lateinit var scene2: Scene
    private lateinit var currentScene: Scene
    private lateinit var transition: Transition
    // transitionSet to hold multiple transitions
    private lateinit var transitionSet : TransitionSet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition)

        // Step 1: Create a Scene object for both the starting and ending layout
        // sceneRoot is id of rootLayout where we want the animation to take place
        scene1 = Scene.getSceneForLayout(sceneRoot, R.layout.scene1, this)
        scene2 = Scene.getSceneForLayout(sceneRoot, R.layout.scene2, this)


        // Step 2: Create a Transition object to define what type of animation you want

        // transition obj from xml
//        transition = TransitionInflater.from(this).inflateTransition(R.transition.example_2)

        // transition obj from code
        val cbTransition = ChangeBounds()
        cbTransition.duration = 500
        cbTransition.interpolator = LinearInterpolator()

        val fadeInTransition = Fade(Fade.IN)
        fadeInTransition.duration = 250
        fadeInTransition.startDelay = 400
        fadeInTransition.addTarget(R.id.txvTitle)

        val fadeOutTransition = Fade(Fade.OUT)
        fadeOutTransition.duration = 50
        fadeOutTransition.addTarget(R.id.txvTitle)

        transitionSet = TransitionSet()
        transitionSet.ordering = TransitionSet.ORDERING_TOGETHER

        transitionSet.addTransition(cbTransition)
        transitionSet.addTransition(fadeInTransition)
        transitionSet.addTransition(fadeOutTransition)


        scene1.enter()
        currentScene = scene1
    }

    fun onClick(view: View) {

        // Step 3: Call TransitionManager.go() to run animation

        if (currentScene === scene1) {
            // move to scene 2 with transition passed
//            TransitionManager.go(scene2, transition)
            TransitionManager.go(scene2, transitionSet)
            currentScene = scene2
        } else {
            TransitionManager.go(scene1, transitionSet)
            currentScene = scene1

        }
    }

    fun goToTransitionWithoutScene(view: View) {
        startActivity(Intent(this, TransitionWithoutScene::class.java))
    }
}
