package com.neo.androidanimations

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.neo.androidanimations.transformers.*
import kotlinx.android.synthetic.main.activity_view_pager_anim.*


/**
 * activity used to implement animation on a ViewPager
 */
class ViewPagerAnimActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager_anim)

        val imageArray = intArrayOf(
            R.drawable.first, R.drawable.second,
            R.drawable.third, R.drawable.fourth, R.drawable.fifth
        )

        // view pager adapter init with an imageArray and passed to the ViewPager
        val adapter = ViewPagerAdapter(this@ViewPagerAnimActivity, imageArray)
        viewPager!!.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_view_pager_anim, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        when (id) {

            R.id.zoom_out       -> setPageTransformer(ZoomOutTransformation())
            R.id.depth_page     -> setPageTransformer(DepthPageTransformation())
            R.id.vertical_flip  -> setPageTransformer(VerticalFlipTransformation())
            R.id.fade_out       -> setPageTransformer(FadeOutTransformation())
            R.id.cube_out       -> setPageTransformer(CubeOutDepthTransformation())
            R.id.hinge          -> setPageTransformer(HingeTransformation())
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setPageTransformer(transformer: ViewPager.PageTransformer ) {
        // sets the animation of the ViewPager with the transformer passed
        viewPager!!.setPageTransformer(true, transformer)
    }
}
