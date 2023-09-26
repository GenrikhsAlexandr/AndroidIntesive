package com.genrikhsalexandr.androidintesive

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.genrikhsalexandr.androidintesive.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sound()
        initSeekBar()
        if (PlayerManager.player != null && PlayerManager.player?.isPlaying == true) {
            binding.pause.isVisible = true
            binding.play.isVisible = false

        } else {
            PlayerManager.getPlayer(this)
            PlayerService.startServicePlayer(this)
        }
    }

    private fun sound() {
        binding.play.setOnClickListener {
            PlayerManager.getPlayer(this)
            PlayerManager.play()
            initSeekBar()
            binding.pause.isVisible = true
            binding.play.isVisible = false
            Log.d("xxx", "play")
        }

        binding.pause.setOnClickListener {
            PlayerManager.pause()
            binding.pause.isVisible = false
            binding.play.isVisible = true
        }
        binding.stop.setOnClickListener {
            PlayerManager.stop()
            binding.pause.isVisible = false
            binding.play.isVisible = true
        }

        binding.next.setOnClickListener {
            PlayerManager.next()
            PlayerManager.getPlayer(this)
            PlayerManager.play()
            initSeekBar()
            binding.pause.isVisible = true
            binding.play.isVisible = false
        }

        binding.previous.setOnClickListener {
            PlayerManager.previous()
            PlayerManager.getPlayer(this)
            PlayerManager.play()
            initSeekBar()
            binding.pause.isVisible = true
            binding.play.isVisible = false
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) PlayerManager.player?.seekTo(progress)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }

    private fun initSeekBar() {
        binding.seekBar.max = PlayerManager.getDuration()

        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    binding.seekBar.progress = PlayerManager.getCurrentPosition()
                    handler.postDelayed(this, 1000)
                } catch (e: Exception) {
                    binding.seekBar.progress = 0
                }
            }
        }, 0)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (PlayerManager.player?.isPlaying==false || PlayerManager.player == null)
            PlayerService.stopServicePlayer(this)
    }
}