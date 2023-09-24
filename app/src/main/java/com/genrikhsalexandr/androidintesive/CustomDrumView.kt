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

    private var customTextView: CustomTextView? = null

    private var animator: ValueAnimator? = null

    private var currentColorIndex = 0

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

    private var currentAngle = 90f
    private val sweepAngle = 360f / rainbowColors.size

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rectF.set(0f, 0f, w.toFloat(), h.toFloat())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val centerX = width / 2f
        val centerY = height / 2f
        val radius = centerX.coerceAtMost(centerY) * 0.8f

        rectF.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius)

        for (i in rainbowColors.indices) {
            drumPaint.color = rainbowColors[i]
            canvas.drawArc(rectF, currentAngle, sweepAngle, true, drumPaint)
            currentAngle += sweepAngle
        }
        val topVertexY = centerY + radius * 0.9f
        val topVertexX = radius * 1.25f

        val bottomLeftX = centerX - radius * 0.1f
        val bottomLeftY = centerY + radius * 1.1f

        val bottomRightX = centerX + radius * 0.1f
        val bottomRightY = centerY + radius * 1.1f

        trianglePath.apply {
            moveTo(topVertexX, topVertexY)
            lineTo(bottomLeftX, bottomLeftY)
            lineTo(bottomRightX, bottomRightY)
            close()
        }
        canvas.drawPath(trianglePath, trianglePaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            startRotationAnimation()
        }
        return super.onTouchEvent(event)
    }

    private fun startRotationAnimation() {
        animator?.cancel()
        val random = Random.nextFloat()
        val end = 8 * (360f - 60f * random)
        val start = currentAngle % 360
        Log.d("xxx", "random $random")
        Log.d("xxx", "currentstart $currentAngle")
        Log.d("xxx", "end$end")
        Log.d("xxx", "start$start")

        animator = ValueAnimator.ofFloat(start, end).apply {
            duration = 2000
            interpolator = PathInterpolator(0.90f, 0.10f, 0.10f, 0.90f)
            addUpdateListener { valueAnimator ->
                currentAngle = valueAnimator.animatedValue as Float
                Log.d("xxx", "currentAngle $currentAngle")
                invalidate()
            }
            animator?.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    // Рандомно выбираем следующий цвет
                    currentColorIndex = Random.nextInt(rainbowColors.size)
                    invalidate()
                }
            })
        }
        animator?.start()
    }

    fun setCustomTextView(customTextView: CustomTextView) {
        this.customTextView = customTextView
    }

    fun callCustomViewBMethod() {
        customTextView?.text
    }

    fun text() {
        when (rainbowColors[currentColorIndex]) {
            0, 2, 4, 6 -> {
                callCustomViewBMethod()
            }
        }
    }

        fun reset() {
            currentAngle = 90f
            invalidate()
        }

    }
