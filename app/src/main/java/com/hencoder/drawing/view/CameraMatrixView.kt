package com.hencoder.drawing.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.hencoder.drawing.R
import com.hencoder.drawing.utils.dp

private val IMAGE_SIZE = 200f.dp
private val IMAGE_PADDING = 100f.dp

class CameraMatrixView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val image = getAvatar(IMAGE_SIZE.toInt())

    private val camera = Camera().apply {
        // 旋转30度
        rotateX(30f)
        // 移动摄像头位置
        setLocation(0f, 0f, -6f * resources.displayMetrics.density)
    }

    override fun onDraw(canvas: Canvas) {

        // 上半部分
        // 流程与下半部分基本相同
        canvas.save()
        canvas.translate(
            (IMAGE_PADDING + IMAGE_SIZE / 2),
            (IMAGE_PADDING + IMAGE_SIZE / 2)
        )
        canvas.rotate(-30f)
        canvas.clipRect(
            - IMAGE_SIZE,
            - IMAGE_SIZE,
            IMAGE_SIZE,
            0f
        )
        canvas.rotate(30f)
        canvas.translate(
            - (IMAGE_PADDING + IMAGE_SIZE / 2),
            - (IMAGE_PADDING + IMAGE_SIZE / 2)
        )
        canvas.drawBitmap(image, IMAGE_PADDING, IMAGE_PADDING, paint)
        canvas.restore()


        // 下半部分
        // 重要：从下到上判断

        canvas.save()

        // 移动画布 - 回到原来位置
        canvas.translate(
            (IMAGE_PADDING + IMAGE_SIZE / 2),
            (IMAGE_PADDING + IMAGE_SIZE / 2)
        )
        // 旋转图片 - 回到原来角度
        canvas.rotate(-30f)

        // 往x轴旋转
        camera.applyToCanvas(canvas)

        // 裁剪图片v2 旋转后需要适当增大裁剪范围
        canvas.clipRect(
            - IMAGE_SIZE,
            0f,
            IMAGE_SIZE,
            IMAGE_SIZE
        )

        // 旋转图片
        canvas.rotate(30f)
        // 移动画布
        canvas.translate(
            - (IMAGE_PADDING + IMAGE_SIZE / 2),
            - (IMAGE_PADDING + IMAGE_SIZE / 2)
        )

//        // 裁剪图片v1
//        canvas.clipRect(
//            IMAGE_PADDING,
//            IMAGE_PADDING + IMAGE_SIZE / 2,
//            IMAGE_PADDING + IMAGE_SIZE,
//            IMAGE_PADDING + IMAGE_SIZE
//        )

        // 绘制图片
        canvas.drawBitmap(image, IMAGE_PADDING, IMAGE_PADDING, paint)
        canvas.restore()
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