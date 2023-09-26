package com.genrikhsalexandr.androidintesive

import android.content.Context
import android.media.MediaPlayer
import android.util.Log

object PlayerManager {

    var player: MediaPlayer? = null
    private var songList = mutableListOf(
        R.raw.johann_strauss_waltz_blue_danube,
        R.raw.fryderyk_chopin_fantasia_imprompt, R.raw.rachmaninov_italian_polka,
        R.raw.rimsky_korsakov_flight_of_the_bumblebee, R.raw.sergei_prokofiev_dance_of_the_knights
    )
    private var currentSongIndex = 0

    fun getCurrentPosition(): Int {
        return player?.currentPosition ?: 0
    }

    fun getDuration():Int{
        return player?.duration ?: 0
    }


    fun getPlayer(context: Context): MediaPlayer {
        if (player == null) {
            player = initPlayer(context.applicationContext)
        }
        return player!!
    }
    private fun initPlayer(context: Context): MediaPlayer? {
        return MediaPlayer.create(
            context,
            songList[currentSongIndex]
        )
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
        if (currentSongIndex < songList.size-1) currentSongIndex++
        else currentSongIndex = 0
        Log.d("xxx","currendSoundIndex $currentSongIndex")
    }

    fun previous() {
        stop()
        if (currentSongIndex > 0) currentSongIndex--
        else currentSongIndex = songList.size - 1
        Log.d("xxx","currendSoundIndex $currentSongIndex")

    }
}