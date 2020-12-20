package com.hencoder.drawing.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.hencoder.drawing.utils.dp
import kotlin.math.cos
import kotlin.math.sin

/**
 * 圆弧半径
 */
private val RADIUS = 150f.dp

/**
 * 扇形集合 总和360
 */
private val ANGLES = floatArrayOf(
    50f, 90f, 160f, 60f
)

/**
 * 扇形颜色集合
 */
private val ANGLES_COLORS = intArrayOf(
    Color.BLUE, Color.GREEN, Color.CYAN, Color.RED
)

/**
 * 选中第x扇形
 */
private const val FOCUS_ANGEL_INDEX = 1

class PieView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint = Paint().apply {
        color = Color.BLUE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {

    }


    override fun onDraw(canvas: Canvas) {
        var startAngel = 0f
        ANGLES.forEachIndexed { index, angel ->
            paint.color = ANGLES_COLORS[index]
            if (index == FOCUS_ANGEL_INDEX) {
                val radians = Math.toRadians(startAngel + angel.toDouble() / 2)
                canvas.save()
                canvas.translate(
                    50f * cos(radians).toFloat(),
                    50f * sin(radians).toFloat(),
                )
            }
            canvas.drawArc(
                width / 2f - RADIUS,
                height / 2f - RADIUS,
                width / 2f + RADIUS,
                height / 2f + RADIUS,
                startAngel,
                angel,
                true,
                paint
            )
            startAngel += angel
            if (index == FOCUS_ANGEL_INDEX) {
                canvas.restore()
            }
        }
    }
}