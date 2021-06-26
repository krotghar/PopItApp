package com.example.popitapp

import android.content.Context
import android.graphics.*
import android.media.MediaPlayer
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

class PopView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStylerAttr: Int = 0,
) : View(context, attrs, defStylerAttr) {

    private var radius = 0f
    private var isPop = false
    private val mp = MediaPlayer.create(context, R.raw.popit)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL

    }

    init {
        isClickable = true
    }

    override fun performClick(): Boolean {
        if (super.performClick()) return true

        isPop = !isPop
        invalidate()
        mp.start()


        return true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        radius = ((min(width,height)/2 * 0.8).toFloat())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val isPopTrueShader = RadialGradient( (height/2).toFloat(), (width/2).toFloat(), radius, Color.argb(97, 0,0, 0), Color.argb(50, 0,0, 0), Shader.TileMode.CLAMP )
        val isPopFalseShader = RadialGradient( (height/2).toFloat(), (width/2).toFloat(), radius, Color.argb(50, 0,0, 0), Color.argb(97, 0,0, 0), Shader.TileMode.CLAMP )
        paint.shader = if (isPop) isPopTrueShader else isPopFalseShader
        canvas.drawCircle((width/2).toFloat(),(height/2).toFloat(), radius, paint )

    }
}