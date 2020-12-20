package com.hencoder.drawing.utils

import android.content.res.Resources
import android.util.TypedValue

private val displayMetrics by lazy(LazyThreadSafetyMode.NONE) {
    Resources.getSystem().displayMetrics
}

/**
 * dp to px
 */
val Float.dp: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, this, displayMetrics
    )
