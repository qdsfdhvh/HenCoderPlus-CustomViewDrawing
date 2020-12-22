package com.hencoder.drawing.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.hencoder.drawing.utils.dp

class XfermodeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bounds = RectF(150f.dp, 50f.dp, 300f.dp, 200f.dp)

    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

    override fun onDraw(canvas: Canvas) {
        val saveCount = canvas.saveLayer(bounds, null)
        paint.color = Color.parseColor("#D81B60")
        canvas.drawOval(200f.dp, 50f.dp, 300f.dp, 150f.dp, paint)
        paint.xfermode = xfermode
        paint.color = Color.parseColor("#2196F3")
        canvas.drawRect(150f.dp, 100f.dp, 250f.dp, 200f.dp, paint)
        paint.xfermode = null
        canvas.restoreToCount(saveCount)
    }

}