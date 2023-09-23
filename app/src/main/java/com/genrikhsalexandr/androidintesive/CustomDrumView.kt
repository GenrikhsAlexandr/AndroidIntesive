package com.genrikhsalexandr.androidintesive

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CustomDrumView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val rainbowColors = listOf(
        Color.RED,
        Color.rgb(255, 150, 0), Color.YELLOW, Color.GREEN,
        Color.rgb(39, 208, 234), Color.BLUE, Color.rgb(82, 20, 168)
    )

    private val values = listOf(20f, 20f, 20f, 25f, 20f, 20f, 20f)
    private val rectF = RectF()
    private var drumPaint = Paint().apply {
        isAntiAlias = true
    }
    private val startAngle = -90f
    private val trianglePaint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.FILL
    }
    private val trianglePath = Path()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val centerX = width / 2f
        val centerY = height / 2f
        val radius = centerX.coerceAtMost(centerY) * 0.8f

        rectF.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius)

        var currentAngle = startAngle

        for (i in rainbowColors.indices) {
            val sweepAngle = 360f * (values[i] / sum(values))
            drumPaint.color = rainbowColors[i]
            canvas.drawArc(rectF, currentAngle, sweepAngle, true, drumPaint)
            currentAngle += sweepAngle
        }

        val topVertexY = centerY + radius * 0.9f
        val topVertexX = radius * 1.25f

        val bottomLeftX = centerX - radius *0.1f
        val bottomLeftY = centerY + radius *1.1f

        val bottomRightX = centerX + radius *0.1f
        val bottomRightY = centerY + radius *1.1f

        trianglePath.apply {
            moveTo(topVertexX, topVertexY)
            lineTo(bottomLeftX, bottomLeftY)
            lineTo(bottomRightX, bottomRightY)
            close()
        }
        canvas.drawPath(trianglePath, trianglePaint)
    }

    private fun sum(list: List<Float>): Float {
        var sum = 0f
        for (value in list) {
            sum += value
        }
        return sum
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
        invalidate()
    }
}