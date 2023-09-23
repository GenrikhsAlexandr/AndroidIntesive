package com.genrikhsalexandr.androidintesive

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CustomDrumView (context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val rainbowColors = listOf(Color.RED, R.color.orange, Color.YELLOW, Color.GREEN, R.color.lightBlue, Color.BLUE, R.color.purple)
    private var currentColorIndex = 0
    private val drumPaint = Paint().apply {
        color = rainbowColors[currentColorIndex]
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height / 2f

        canvas.drawCircle(centerX, centerY, 300f, drumPaint)

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            startRotationAnimation()
        }
        return super.onTouchEvent(event)
    }

    private fun startRotationAnimation() {
        TODO()
    }

    fun reset() {
        currentColorIndex = 0
        invalidate()
    }
}
