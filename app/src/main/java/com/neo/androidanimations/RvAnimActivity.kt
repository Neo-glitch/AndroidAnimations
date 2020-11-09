package com.neo.androidanimations

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.SlideInLeftAnimationAdapter
import jp.wasabeef.recyclerview.animators.ScaleInBottomAnimator
import jp.wasabeef.recyclerview.animators.ScaleInLeftAnimator
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import kotlinx.android.synthetic.main.activity_rv_anim.*


/**
 * class to implement RV anims using open source lib
 */
class RvAnimActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_rv_anim)

		setUpRecyclerView()
	}

	private fun setUpRecyclerView() {

		val adapter = RecyclerAdapter(this, Landscape.data)
//		recyclerView.adapter = adapter
		// applies animation on the adapter obj, when scrolling
		recyclerView.adapter = SlideInLeftAnimationAdapter(adapter)

		val layoutManager = LinearLayoutManager(this)
		layoutManager.orientation = LinearLayoutManager.VERTICAL 
		recyclerView.layoutManager = layoutManager

		// sets the animator for the RV list items
		recyclerView.itemAnimator = SlideInUpAnimator()

		// extra stuff(optional)
		recyclerView.itemAnimator?.apply {
			addDuration = 500		// duration of addition of item to RV
			removeDuration = 500    // duration of removal of item fro the Rv
		}
	}

	fun goToViewPagerAnimActivity(view: View) {
		startActivity(Intent(this, ViewPagerAnimActivity::class.java	))
	}
}

