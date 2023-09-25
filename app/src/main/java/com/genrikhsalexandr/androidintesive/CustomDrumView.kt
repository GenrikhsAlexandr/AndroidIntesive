package com.genrikhsalexandr.androidintesive

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.PathInterpolator
import kotlin.random.Random

class CustomDrumView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    interface Listener {
        fun onDrumSpinned(position: Int)
    }

    private var animator: ValueAnimator? = null
    private val rainbowColors = listOf(
        Color.RED,
        Color.rgb(255, 150, 0), Color.YELLOW, Color.GREEN,
        Color.rgb(39, 208, 234), Color.BLUE, Color.rgb(82, 20, 168)
    )
    private val rectF = RectF()
    private var drumPaint = Paint().apply {
        isAntiAlias = true
    }
    private val trianglePaint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.FILL
    }
    private val trianglePath = Path()
    private val sweepAngle = 360f / rainbowColors.size
    private var currentAngle = 0f

    private var isSpinning = false

    private var centerX = 0f
    private var centerY = 0f
    private var radius = 0f

    private var topVertexY = 0f
    private var topVertexX = 0f

    private var bottomLeftX = 0f
    private var bottomLeftY = 0f

    private var bottomRightX = 0f
    private var bottomRightY = 0f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = width / 2f
        centerY = height / 2f
        radius = centerX.coerceAtMost(centerY) * 0.8f
        rectF.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius)

        topVertexY = centerY + radius * 0.9f
        topVertexX = radius * 1.25f

        bottomLeftX = centerX - radius * 0.1f
        bottomLeftY = centerY + radius * 1.1f

        bottomRightX = centerX + radius * 0.1f
        bottomRightY = centerY + radius * 1.1f

        trianglePath.apply {
            moveTo(topVertexX, topVertexY)
            lineTo(bottomLeftX, bottomLeftY)
            lineTo(bottomRightX, bottomRightY)
            close()
        }
        Log.d("xxx", " centerX $centerX")
        Log.d("xxx", " centerY $centerY")
        Log.d("xxx", " radius $radius")
        Log.d("xxx", " rectF $rectF")

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (i in rainbowColors.indices) {
            drumPaint.color = rainbowColors[i]
            canvas.drawArc(rectF, currentAngle, sweepAngle, true, drumPaint)
            currentAngle += sweepAngle
        }
        canvas.drawPath(trianglePath, trianglePaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (isSpinning) {
            return false
        }
        if (event.action == MotionEvent.ACTION_DOWN) {
            startRotationAnimation()
        }
        return super.onTouchEvent(event)
    }

    var listener: Listener? = null

    private fun startRotationAnimation() {
        animator?.cancel()
        val random = Random.nextFloat()
        val end = 8 * (360f - 60f * random)
        val start = currentAngle % 360
        Log.d("xxx", "currentstart $currentAngle")
        Log.d("xxx", "end${end % 360}")
        Log.d("xxx", "start$start")

        isSpinning = true
        animator = ValueAnimator.ofFloat(start, end).apply {
            duration = 2000
            interpolator = PathInterpolator(0.90f, 0.10f, 0.10f, 0.90f)
            addUpdateListener { valueAnimator ->
                currentAngle = valueAnimator.animatedValue as Float
                invalidate()
            }
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    val position = ((currentAngle %360f+(2*sweepAngle-90f))/ sweepAngle).toInt()
                    Log.d("xxx", "onAnimationEnd $position")
                    isSpinning = false
                    listener?.onDrumSpinned(position)
                }
            })
        }
        animator?.start()
    }
}
