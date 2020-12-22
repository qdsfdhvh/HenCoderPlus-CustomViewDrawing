package com.hencoder.drawing.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.hencoder.drawing.R
import com.hencoder.drawing.utils.dp

//private val IMAGE_WIDTH = 200f.dp
private val IMAGE_PADDING = 20f.dp

class AvatarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

    override fun onDraw(canvas: Canvas) {
        val imageWidth = width
        val borderSize = 40
        canvas.drawOval(
            IMAGE_PADDING - borderSize,
            IMAGE_PADDING - borderSize,
            imageWidth - IMAGE_PADDING + borderSize,
            imageWidth - IMAGE_PADDING + borderSize,
            paint
        )
        // 启用离屏缓冲
        val saveCount = canvas.saveLayer(
            IMAGE_PADDING,
            IMAGE_PADDING,
            imageWidth - IMAGE_PADDING,
            imageWidth - IMAGE_PADDING,
            null
        )
        canvas.drawOval(
            IMAGE_PADDING,
            IMAGE_PADDING,
            imageWidth - IMAGE_PADDING,
            imageWidth - IMAGE_PADDING,
            paint
        )
        paint.xfermode = xfermode
        canvas.drawBitmap(
            getAvatar((imageWidth - IMAGE_PADDING * 2).toInt()),
            IMAGE_PADDING,
            IMAGE_PADDING,
            paint
        )
        // 关闭离屏缓冲
        canvas.restoreToCount(saveCount)
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