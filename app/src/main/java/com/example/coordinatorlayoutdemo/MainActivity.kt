package com.example.coordinatorlayoutdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.widget.NestedScrollView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout

class MainActivity : AppCompatActivity() {
    private var actionBarHeight: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val appBarLayout: AppBarLayout = findViewById(R.id.appBarLayout)
        val defaultAppBar: LinearLayout = findViewById(R.id.defaultAppBar)
        val nestedScrollView: NestedScrollView = findViewById(R.id.nested_scroll_view)
        val collapsingToolbarLayout: CollapsingToolbarLayout =
            findViewById(R.id.collapsingToolbarLayout)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Scroll NestedScrollView by half of the height of AppBarLayout.
        nestedScrollView.post {
            val collapseHeight = (resources.getDimension(R.dimen.appBarHeight) * .5).toInt()
            nestedScrollView.startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL)
            nestedScrollView.dispatchNestedPreScroll(0, collapseHeight, null, null)
        }

        actionBarHeight = Utility.getAppBarSize(this)
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, _ ->
            defaultAppBar.visibility =
                if ((resources.getDimension(R.dimen.appBarHeight) - actionBarHeight + appBarLayout.y) == 0F) View.VISIBLE else View.GONE
        })
    }
}