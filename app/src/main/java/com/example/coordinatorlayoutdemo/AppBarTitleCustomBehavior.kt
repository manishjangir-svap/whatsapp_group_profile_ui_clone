package com.example.coordinatorlayoutdemo

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.get

class AppBarTitleCustomBehavior(context: Context, attributeSet: AttributeSet): CoordinatorLayout.Behavior<View>(context, attributeSet) {
    private var actionBarHeight = 0

    init {
        actionBarHeight = Utility.getAppBarSize(context)
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        val finalTextSize = 28
        val initialTextSize = 20
        val halfHeight = dependency.context.resources.getDimension(R.dimen.appBarHeight) * 0.5 * -1

        child.y = dependency.y

        if(dependency.y < halfHeight) {
            val percentage = (dependency.y - halfHeight) / (halfHeight + actionBarHeight)
            child.x = (56 * percentage * dependency.context.resources.displayMetrics.density).toFloat()
            child.layoutParams.width = (dependency.context.resources.displayMetrics.widthPixels - child.x).toInt()

            val constraintLayout = child as ConstraintLayout
            val linearLayout = constraintLayout[0] as LinearLayout
            val textView = linearLayout[0] as TextView

            val textSizeDifference = (finalTextSize - initialTextSize) * percentage
            textView.textSize = (finalTextSize - textSizeDifference).toFloat()
        }

        return true
    }
}