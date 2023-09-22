package com.genrikhsalexandr.androidintesive

import android.content.Context
import android.media.MediaPlayer
import android.util.Log

object PlayerManager {

    var player: MediaPlayer? = null
    private var currentSong = mutableListOf(
        R.raw.johann_strauss_waltz_blue_danube,
        R.raw.fryderyk_chopin_fantasia_imprompt, R.raw.rachmaninov_italian_polka,
        R.raw.rimsky_korsakov_flight_of_the_bumblebee, R.raw.sergei_prokofiev_dance_of_the_knights
    )
    private var currentSongIndex = 0

    fun initPlayer(context: Context) {
        player = MediaPlayer.create(
            context,
            currentSong[currentSongIndex]
        )
        Log.d("Manager", "init Player")
    }

    fun play() {
        player?.start()
    }

    fun stop() {
        player?.stop()
        player?.release()
        player = null
    }

    fun pause() {
        player?.pause()
    }

    fun next() {
        stop()
        currentSongIndex++
        if (currentSongIndex >= currentSong.size) currentSongIndex =
            0
    }

    fun previous() {
        stop()
        currentSongIndex--
        if (currentSongIndex < 0) currentSongIndex =
            currentSong.size - 1
    }
}