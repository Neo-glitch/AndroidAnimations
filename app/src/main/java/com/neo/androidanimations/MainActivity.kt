package com.neo.androidanimations

import android.animation.*
import android.os.Bundle
import android.view.View
import android.view.animation.BounceInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), Animator.AnimatorListener {

    // for applying animation using xml
//	private var rotateAnimator: Animator? = null
//	private var translateAnimator:Animator? = null
//	private var scaleAnimator:Animator? = null
//	private var fadeAnimator:Animator? = null


    // for applying animation using code
    private var rotateAnimator: ObjectAnimator? = null
    private var translateAnimator: ObjectAnimator? = null
    private var scaleAnimator: ObjectAnimator? = null
    private var fadeAnimator: ObjectAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun rotateAnimation(view: View) {

//		// loads the animation from the animator xml and apply to the imageView(targetImage)
//		rotateAnimator = AnimatorInflater.loadAnimator(this, R.animator.rotate)
//		rotateAnimator?.apply {
//			setTarget(targetImage)
//			addListener(this@MainActivity)
//			start()
//		}


        rotateAnimator = ObjectAnimator.ofFloat(targetImage, "rotation", 0.0f, -180.0f)
        rotateAnimator?.apply {
            duration = 1000
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
            addListener(this@MainActivity)
            start()
        }
    }

    fun scaleAnimation(view: View) {

//		scaleAnimator = AnimatorInflater.loadAnimator(this, R.animator.scale)
//		scaleAnimator?.apply {
//			setTarget(targetImage)
//			addListener(this@MainActivity)
//			start()
//		}

        scaleAnimator = ObjectAnimator.ofFloat(targetImage, "scaleX", 1.0f, 3.0f)
        scaleAnimator?.apply {
            duration = 1000
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
            addListener(this@MainActivity)
            start()
        }


    }

    fun translateAnimation(view: View) {

//		translateAnimator = AnimatorInflater.loadAnimator(this, R.animator.translate)
//		translateAnimator?.apply {
//			setTarget(targetImage)
//			addListener(this@MainActivity)
//			start()
//		}

        translateAnimator = ObjectAnimator.ofFloat(targetImage, "translationX", 0f, 200f)
        translateAnimator?.apply {
            duration = 1000
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
            addListener(this@MainActivity)
            start()
        }


    }

    fun fadeAnimation(view: View) {
//		val fadeAnimator = AnimatorInflater.loadAnimator(this, R.animator.alpha)
//		fadeAnimator?.apply {
//			setTarget(targetImage)
//			addListener(this)
//			start()
//		}
//
//		// can be done this way, but better way is one above
//		fadeAnimator = AnimatorInflater.loadAnimator(this, R.animator.alpha)
//		fadeAnimator?.setTarget(targetImage)
//		fadeAnimator?.addListener(this@MainActivity)
//		fadeAnimator?.start()

        fadeAnimator = ObjectAnimator.ofFloat(targetImage, "alpha", 1.0f, 0.0f)
        fadeAnimator?.apply {
            duration = 1500
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
            addListener(this@MainActivity)
            start()
        }


    }


    // Implementation of AnimatorListener interface
    override fun onAnimationStart(animation: Animator?) {
        when (animation) {
            scaleAnimator -> {
                Toast.makeText(this, "Scale Animation Started", Toast.LENGTH_SHORT).show()
            }
            rotateAnimator -> {
                Toast.makeText(this, "Rotate Animation Started", Toast.LENGTH_SHORT).show()
            }
            translateAnimator -> {
                Toast.makeText(this, "Translate Animation Started", Toast.LENGTH_SHORT).show()
            }
            fadeAnimator -> {
                Toast.makeText(this, "Fade Animation Started", Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun onAnimationRepeat(animation: Animator?) {

        Toast.makeText(this, "Animation Repeated", Toast.LENGTH_SHORT).show()
    }

    override fun onAnimationEnd(animation: Animator?) {

        Toast.makeText(this, "Animation Ended", Toast.LENGTH_SHORT).show()
    }

    override fun onAnimationCancel(animation: Animator?) {
        // calls onAnimationEnd and called only when .onCancel() is called on animator

        Toast.makeText(this, "Animation Cancelled", Toast.LENGTH_SHORT).show()
    }

    fun setFromXML(view: View) {

        val animator = AnimatorInflater.loadAnimator(this, R.animator.set)
        animator.apply {
            setTarget(targetImage)
            start()
        }
    }

    /////  end of animator interface method


    fun setFromCode(view: View) {
        // Root Animator Set
        val rootSet = AnimatorSet()

		// Flip Animation
        val flip = ObjectAnimator.ofFloat(targetImage, "rotationX", 0.0f, 360.0f)
        flip.duration = 500

		// Child Animator Set
        val childSet = AnimatorSet()

		// Scale Animations
        val scaleX = ObjectAnimator.ofFloat(targetImage, "scaleX", 1.0f, 1.5f)
        scaleX.duration = 500
        scaleX.interpolator = BounceInterpolator()

        val scaleY = ObjectAnimator.ofFloat(targetImage, "scaleY", 1.0f, 1.5f)
        scaleY.duration = 500
        scaleY.interpolator = OvershootInterpolator()

		// to order animations so that flip animation runs before anim in childSet that has two anim running together
//		rootSet.playSequentially(flip, childSet)
//		childSet.playTogether(scaleX, scaleY)

        rootSet.play(flip).before(childSet)
        childSet.play(scaleX).with(scaleY)

		rootSet.start()
    }


    fun viewPropertyAnimator(view: View) {
        val viewPropAnim = targetImage.animate()

        // all anim here run at same time
        viewPropAnim.apply {
            duration = 1000
            rotationX(360.0f)
            scaleX(1.5f)
            scaleY(1.5f)
            translationX(200.0f)
            alpha(0.5f)
            interpolator = OvershootInterpolator()
        }
    }


    fun propertyValuesHolder(view: View) {
        // runs all anim simultaneously but needs object Animator obj so listener can be attached to it unlike VPA
        val rotationX = PropertyValuesHolder.ofFloat("rotationX", 360f)
        val scaleX = PropertyValuesHolder.ofFloat("ScaleX", 1.5f)
        val scaleY = PropertyValuesHolder.ofFloat("ScaleY", 1.5f)

        val objAnimator = ObjectAnimator.ofPropertyValuesHolder(targetImage, rotationX, scaleX, scaleY)
        objAnimator.apply {
            duration = 1000
            interpolator = OvershootInterpolator()
            start()
        }


    }
}
