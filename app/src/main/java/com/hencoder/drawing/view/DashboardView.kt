package com.hencoder.drawing.view

import android.content.Context
import android.graphics.*
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
 * 线段长度
 */
private val LINE_HEIGHT = 120f.dp

/**
 * 刻度线宽度&长度
 */
private val DASH_WIDTH = 2f.dp
private val DASH_LENGTH = 5f.dp

/**
 * 分为20个刻度
 */
private const val DASH_SIZE = 20

/**
 * 绘制仪表盘
 */
class DashboardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val path = Path()

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        // 颜色
        color = Color.BLUE
        // 绘制线条
        style = Paint.Style.STROKE
        // 线条宽度
        strokeWidth = 3f.dp
    }

    private val dashPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
    }

    private val dash = Path().apply {
        addRect(0f, 0f, DASH_WIDTH, DASH_LENGTH, Path.Direction.CW)
    }

    /**
     * 开口角度
     */
    private val openAngle = 120f

    /**
     * 指针弧度
     */
    private var lineRadians = 0f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        path.reset()
        path.addArc(
            width / 2f - RADIUS,
            height / 2f - RADIUS,
            width / 2f + RADIUS,
            height / 2f + RADIUS,
            90 + openAngle / 2f,
            360 - openAngle,
        )

        val pathMeasure = PathMeasure(path, false)
        // 需要减去最后一个点的宽度再计算间隔
        val phase = (pathMeasure.length - DASH_WIDTH) / DASH_SIZE
        // 确定实际间隔，重新生成dashPathEffect
        dashPaint.pathEffect = PathDashPathEffect(
            dash, phase, 0f, PathDashPathEffect.Style.ROTATE
        )

        // 第x个刻度
        val count = 6
        // 此刻度的角度
        val lineAngle = (90 + openAngle / 2) + (360 - openAngle) / DASH_SIZE * count
        // 角度转弧度
        lineRadians = Math.toRadians(lineAngle.toDouble()).toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        // 画圆弧
        canvas.drawPath(path, paint)
        // 画刻度
        canvas.drawPath(path, dashPaint)
        // 画指针(直线)
        canvas.drawLine(
            width / 2f,
            height / 2f,
            (width / 2f + cos(lineRadians) * LINE_HEIGHT),
            (height / 2f + sin(lineRadians) * LINE_HEIGHT),
            paint
        )
    }

}