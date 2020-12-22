package com.hencoder.drawing.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.hencoder.drawing.utils.dp

class XfermodeBitmapView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bounds = RectF(150f.dp, 50f.dp, 300f.dp, 200f.dp)

    private val circleBitmap = Bitmap.createBitmap(
        150f.dp.toInt(), 150f.dp.toInt(), Bitmap.Config.ARGB_8888
    )

    private val squareBitmap = Bitmap.createBitmap(
        150f.dp.toInt(), 150f.dp.toInt(), Bitmap.Config.ARGB_8888
    )

    init {
        val canvas = Canvas(circleBitmap)
        paint.color = Color.parseColor("#D81B60")
        canvas.drawOval(50f.dp, 0f.dp, 150f.dp, 100f.dp, paint)

        canvas.setBitmap(squareBitmap)
        paint.color = Color.parseColor("#2196F3")
        canvas.drawRect(0f.dp, 50f.dp, 100f.dp, 150f.dp, paint)
    }

    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.DARKEN)

    override fun onDraw(canvas: Canvas) {
        val saveCount = canvas.saveLayer(bounds, null)
        canvas.drawBitmap(circleBitmap, 150f.dp, 50f.dp, paint)
        paint.xfermode = xfermode
        canvas.drawBitmap(squareBitmap, 150f.dp, 50f.dp, paint)
        paint.xfermode = null
        canvas.restoreToCount(saveCount)
    }

}