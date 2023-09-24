package com.genrikhsalexandr.androidintesive

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatTextView

class CustomTextView(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {
    private val paint = Paint()
    private val rectF = RectF()

    init {
        paint.color = Color.BLACK // Цвет текста
        paint.textSize = 68f // Размер текста в пикселях
        paint.typeface = Typeface.create(Typeface.createFromAsset(context.assets, "Creamy Dough.otf"), Typeface.BOLD) // Стиль шрифта
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val centerX = width / 2f-10f
        val centerY:Float = height.toFloat()
        var text = "Hello"
        canvas.drawText(text, centerX, centerY, paint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rectF.left = 20f
        rectF.top = 40f
        rectF.right = w - 20f
        rectF.bottom = h - 40f
    }
}