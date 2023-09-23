package com.genrikhsalexandr.androidintesive

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatTextView

class CustomTextView(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {
    private val textPaint = Paint().apply {
        strokeWidth = 150f
        textSize = 150f
        style = Paint.Style.STROKE
        Log.d("xxx", "text ${Paint()}")
    }

    init {
        applyFont()
    }

    private fun applyFont() {
        val typeface = Typeface.createFromAsset(context.assets, "Creamy Dough.otf")
        setTypeface(typeface)
    }
/*
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawText(text.toString(), 0f, 0f, textPaint)
        Log.d("xxx", "conva ${textPaint}")
    }*/
}