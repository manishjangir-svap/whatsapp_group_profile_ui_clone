package com.example.coordinatorlayoutdemo

import android.content.Context
import android.util.TypedValue

object Utility {

    fun getAppBarSize(context: Context): Int {
        var actionBarHeight = 0

        val typedValue = TypedValue()
        if(context.theme.resolveAttribute(android.R.attr.actionBarSize, typedValue, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(typedValue.data, context.resources.displayMetrics)
        }

        return actionBarHeight
    }

}