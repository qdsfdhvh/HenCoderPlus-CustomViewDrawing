package com.hencoder.drawing.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.hencoder.drawing.R
import com.hencoder.drawing.utils.dp

private val IMAGE_SIZE = 200f.dp
private val IMAGE_PADDING = 100f.dp

class CameraView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val image = getAvatar(IMAGE_SIZE.toInt())

    private val clipped = Path().apply {
        addOval(
            IMAGE_PADDING, IMAGE_PADDING,
            IMAGE_PADDING + IMAGE_SIZE,
            IMAGE_PADDING + IMAGE_SIZE,
            Path.Direction.CW
        )
    }

    override fun onDraw(canvas: Canvas) {
        // 方形裁剪
//        canvas.clipRect(
//            IMAGE_PADDING,
//            IMAGE_PADDING,
//            IMAGE_PADDING + IMAGE_SIZE / 2,
//            IMAGE_PADDING + IMAGE_SIZE / 2,
//        )
        // 自定义裁剪
        // PS: 通过这种方式裁剪圆角图片，边角会很粗糙，无法处理
        canvas.clipPath(clipped)
        canvas.drawBitmap(image, IMAGE_PADDING, IMAGE_PADDING, paint)
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