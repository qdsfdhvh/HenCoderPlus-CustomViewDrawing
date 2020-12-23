package com.hencoder.drawing.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.hencoder.drawing.R
import com.hencoder.drawing.utils.dp

private val IMAGE_SIZE = 150f.dp

class MultiLineTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    /**
     * Lorem ipsum
     * https://www.lipsum.com/feed/html
     */
    private val text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
            "Nulla nec est porttitor, lobortis ligula ut, vehicula sem. " +
            "Cras interdum sed enim at bibendum. Sed rutrum, metus a venenatis fringilla," +
            " nisi nisi pellentesque enim, vulputate tincidunt turpis mauris sodales nisi." +
            " Nulla fermentum nulla a turpis bibendum fringilla. " +
            "Fusce ut turpis maximus enim sagittis scelerisque." +
            " Nullam et libero eleifend purus auctor facilisis eu vel ligula." +
            " Mauris quis mauris vel nibh viverra tempor et interdum lorem. " +
            "In eget lacinia enim, sed pretium sapien. " +
            "Maecenas laoreet orci ac odio feugiat lobortis. " +
            "Ut sed porttitor tellus. Fusce sit amet elit tortor. " +
            "Suspendisse eleifend purus eget ex tristique, " +
            "sed egestas orci viverra. Proin rhoncus scelerisque iaculis."

    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 20f.dp
    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 20f.dp
    }

    private val fontMetrics = Paint.FontMetrics()

    override fun onDraw(canvas: Canvas) {
        // 一般多行文字绘制方式
//        val staticLayout = StaticLayout.Builder.obtain(text, 1, text.length, textPaint, width)
//            .setAlignment(Layout.Alignment.ALIGN_NORMAL)
//            .setIncludePad(false)
//            .build()
//        staticLayout.draw(canvas)

        val imageY = 50f.dp

        canvas.drawBitmap(
            getAvatar(IMAGE_SIZE.toInt()),
            width - IMAGE_SIZE,
            imageY,
            paint
        )
        paint.getFontMetrics(fontMetrics)

        val measureWidth = floatArrayOf(0f)
        var verticalOffset = -fontMetrics.top

        var textMaxWidth: Float

        var startIndex = 0
        var count: Int
        while (startIndex < text.length) {
            // 当前文字底部<图片y轴 || 当前文字顶部>图片y轴+图片高度
            textMaxWidth = if (
                verticalOffset + fontMetrics.bottom < imageY
                || verticalOffset + fontMetrics.top > imageY + IMAGE_SIZE
            ) {
                width.toFloat()
            } else {
                width.toFloat() - IMAGE_SIZE
            }

            // 计算出当前行做多绘制的文字个数
            count = paint.breakText(
                text, startIndex, text.length, true,
                textMaxWidth, measureWidth
            )

            // 绘制当前行
            canvas.drawText(
                text, startIndex, startIndex + count,
                0f, verticalOffset,
                paint
            )

            startIndex += count
            verticalOffset += paint.fontSpacing
        }
    }

    private fun getAvatar(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        // 只获取图片参数
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.avatar_rengwuxian, options)
        // 解析图片
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.avatar_rengwuxian, options)
    }

}