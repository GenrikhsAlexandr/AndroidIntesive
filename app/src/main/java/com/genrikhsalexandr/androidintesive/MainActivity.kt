package com.genrikhsalexandr.androidintesive

import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.genrikhsalexandr.androidintesive.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.seekbar.progress = 50

        val drumView = binding.drum

        binding.reset.setOnClickListener {
            drumView.reset()
            with(binding) {
                customTV.isVisible = true
                Picasso.get().load("https://picsum.photos/id/12/200")
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.color.black)
                    .into(image)
            }
        }

        binding.seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Вызывается при изменении значения SeekBar
                val newSize =
                    calculateNewSize(progress) // Функция для вычисления нового размера ImageView
                updateImageViewSize(newSize) // Функция для обновления размера ImageView
                drumView.reset()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Вызывается при начале трекинга (прикосновении к SeekBar)
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Вызывается при завершении трекинга (отпускании SeekBar)
            }
        })
    }

    private fun calculateNewSize(progress: Int): Int {
        val minSize = 900 // Минимальный размер (0 на SeekBar)
        val maxSize = 1000 // Максимальный размер (100 на SeekBar)

        // Вычисляем новый размер на основе progress
        val newSize = minSize + (maxSize - minSize) * progress / 100


        return newSize
    }

    private fun updateImageViewSize(newSize: Int) {
        val layoutParams = binding.drum.layoutParams
        layoutParams.width = newSize // Устанавливаем новую ширину
        layoutParams.height = newSize // Устанавливаем новую высоту (если нужно)
        binding.drum.layoutParams = layoutParams
    }

}