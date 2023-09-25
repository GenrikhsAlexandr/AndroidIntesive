package com.genrikhsalexandr.androidintesive

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class CustomTextView(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {
    private val paint = Paint()

    init {
        paint.color = Color.BLACK
        paint.textSize = 78f
        paint.typeface = Typeface.create(
            Typeface.createFromAsset(context.assets, "Creamy Dough.otf"),
            Typeface.BOLD)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val centerX = width / 2f-15f
        val centerY:Float = height.toFloat()
        val text = "Hello"
        canvas.drawText(text, centerX, centerY, paint)
    }
}