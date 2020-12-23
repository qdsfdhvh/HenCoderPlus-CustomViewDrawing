package com.hencoder.drawing.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.hencoder.drawing.R
import com.hencoder.drawing.utils.dp

private val RADIUS = 150f.dp

private val STROKE_WIDTH = 20f.dp

class SportView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 100f.dp
        textAlign = Paint.Align.CENTER
        typeface = ResourcesCompat.getFont(context, R.font.font)
    }

    private val fontMetrics = Paint.FontMetrics()

    override fun onDraw(canvas: Canvas) {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = STROKE_WIDTH
        paint.color = Color.parseColor("#90A4AE")
        canvas.drawCircle(
            width / 2f,
            height / 2f,
            RADIUS,
            paint
        )

        paint.color = Color.parseColor("#FF4081")
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawArc(
            width / 2f - RADIUS,
            height / 2f - RADIUS,
            width / 2f + RADIUS,
            height / 2f + RADIUS,
            -90f,
            225f,
            false,
            paint
        )

        paint.style = Paint.Style.FILL
        // 获取文字边界
        paint.getFontMetrics(fontMetrics)

        val move = (fontMetrics.ascent + fontMetrics.descent) / 2

        canvas.drawText(
            "abab",
            width / 2f,
            height / 2f - move,
            paint
        )

    }

}