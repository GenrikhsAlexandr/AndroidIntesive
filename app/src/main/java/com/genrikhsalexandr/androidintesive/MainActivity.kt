package com.genrikhsalexandr.androidintesive

import android.app.ActivityManager
import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
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
        if (PlayerManager.player==null) {
            PlayerManager.initPlayer()
            PlayerService.startServicePlayer(this)
            sound()
        } else {
            sound()
            initSeekBar()
            binding.pause.isVisible = true
            binding.play.isVisible = false
        }
    }

    private fun sound() {
        binding.play.setOnClickListener {
            if (PlayerManager.player==null)
                PlayerManager.player = MediaPlayer.create(
                    this.applicationContext,
                    PlayerManager.currentSong[PlayerManager.currentSongIndex]
                )
                PlayerManager.player?.start()
                initSeekBar()
                binding.pause.isVisible = true
                binding.play.isVisible = false

        }

        binding.pause.setOnClickListener {
            if (PlayerManager.player != null) {
                PlayerManager.player?.pause()
                binding.pause.isVisible = false
                binding.play.isVisible = true
            }
        }
        binding.stop.setOnClickListener {
            if (PlayerManager.player != null) {
                PlayerManager.player?.stop()
                PlayerManager.player?.release()
                PlayerManager.player = null
                binding.pause.isVisible = false
                binding.play.isVisible = true
            }
        }

        binding.next.setOnClickListener {
            if (PlayerManager.player != null) {
                PlayerManager.player?.stop()
                PlayerManager.player?.release()
                PlayerManager.player = null
                PlayerManager.currentSongIndex++
                if (PlayerManager.currentSongIndex >= PlayerManager.currentSong.size) PlayerManager.currentSongIndex =
                    0
                PlayerManager.player = MediaPlayer.create(
                    this,
                    PlayerManager.currentSong[PlayerManager.currentSongIndex]
                )
                PlayerManager.player?.start()
                initSeekBar()
                binding.pause.isVisible = true
                binding.play.isVisible = false
            }
        }

        binding.previous.setOnClickListener {
            if (PlayerManager.player != null) {
                PlayerManager.player?.stop()
                PlayerManager.player?.release()
                PlayerManager.player = null
                PlayerManager.currentSongIndex--
                if (PlayerManager.currentSongIndex < 0) PlayerManager.currentSongIndex =
                    PlayerManager.currentSong.size - 1
                PlayerManager.player = MediaPlayer.create(
                    this,
                    PlayerManager.currentSong[PlayerManager.currentSongIndex]
                )
                PlayerManager.player?.start()
                initSeekBar()
                binding.pause.isVisible = true
                binding.play.isVisible = false
            }
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
        binding.seekBar.max = PlayerManager.player!!.duration

        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    binding.seekBar.progress = PlayerManager.player!!.currentPosition
                    handler.postDelayed(this, 1000)
                } catch (e: Exception) {
                    binding.seekBar.progress = 0
                }
            }
        }, 0)
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}