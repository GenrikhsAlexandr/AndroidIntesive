package com.genrikhsalexandr.androidintesive

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.genrikhsalexandr.androidintesive.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var player: MediaPlayer? = null
    private var currentSong = mutableListOf(
        R.raw.johann_strauss_waltz_blue_danube,
        R.raw.fryderyk_chopin_fantasia_imprompt, R.raw.rachmaninov_italian_polka,
        R.raw.rimsky_korsakov_flight_of_the_bumblebee, R.raw.sergei_prokofiev_dance_of_the_knights
    )
    private var currentSongIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sound()
    }

    private fun sound() {
        binding.play.setOnClickListener {
            if (player == null)
                player = MediaPlayer.create(this, currentSong[currentSongIndex])
            player?.start()
            initSeekBar()
            binding.pause.isVisible = true
            binding.play.isVisible = false

        }

        binding.pause.setOnClickListener {
            if (player != null) {
                player?.pause()
                binding.pause.isVisible = false
                binding.play.isVisible = true
            }
        }
        binding.stop.setOnClickListener {
            if (player != null) {
                player?.stop()
                player?.release()
                player = null
                binding.pause.isVisible = false
                binding.play.isVisible = true
            }
        }

        binding.next.setOnClickListener {
            if (player != null) {
                player?.stop()
                player?.release()
                player = null
                currentSongIndex++
                if (currentSongIndex >= currentSong.size) currentSongIndex = 0
                player = MediaPlayer.create(this, currentSong[currentSongIndex])
                player?.start()
                initSeekBar()
                binding.pause.isVisible = true
                binding.play.isVisible = false
            }
        }

        binding.previous.setOnClickListener {
            if (player != null) {
                player?.stop()
                player?.release()
                player = null
                currentSongIndex--
                if (currentSongIndex < 0) currentSongIndex = currentSong.size - 1
                player = MediaPlayer.create(this, currentSong[currentSongIndex])
                player?.start()
                initSeekBar()
                binding.pause.isVisible = true
                binding.play.isVisible = false
            }
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) player?.seekTo(progress)
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }

    private fun initSeekBar() {
        binding.seekBar.max = player!!.duration

        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    binding.seekBar.progress = player!!.currentPosition
                    handler.postDelayed(this, 1000)
                } catch (e: Exception) {
                    binding.seekBar.progress = 0
                }
            }
        }, 0)
    }
}