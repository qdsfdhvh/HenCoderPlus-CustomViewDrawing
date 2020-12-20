package com.hencoder.drawing.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.hencoder.drawing.utils.dp

private const val RADIUS = 50f

class TestView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    // ANTI_ALIAS_FLAG 开启抗锯齿
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val path = Path()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        path.reset()
        val radius = RADIUS.dp
        val radius2 = radius / 2
        // 添加圆
        path.addCircle(
            width / 2f,
            height / 2f,
            radius,
            // clockwise：顺时针
            // counter-clockwise：逆时针
            Path.Direction.CW
        )
        path.addCircle(
            width / 2f,
            height / 2f,
            radius2,
            Path.Direction.CW
        )
        // 添加方形
        path.addRect(
            width / 2f - radius,
            height / 2f,
            width / 2f + radius,
            height / 2f + radius * 2,
            Path.Direction.CW
        )
        // WINDING 默认
        // ODD 镂空
        // INNER_ODD 反过来镂空
        path.fillType = Path.FillType.EVEN_ODD
    }

    override fun onDraw(canvas: Canvas) {
        // 画线
//        canvas.drawLine(100f, 100f, 200f, 200f, paint)
        // 画圆
//        canvas.drawCircle(width / 2f, height / 2f, RADIUS.dp, paint)
        // 画路径
        canvas.drawPath(path, paint)
    }


}