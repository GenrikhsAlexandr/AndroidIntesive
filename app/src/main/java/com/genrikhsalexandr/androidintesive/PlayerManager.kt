package com.genrikhsalexandr.androidintesive

import android.media.MediaPlayer
import android.util.Log

object PlayerManager{

    var player: MediaPlayer? = null
    var currentSong = mutableListOf(
        R.raw.johann_strauss_waltz_blue_danube,
        R.raw.fryderyk_chopin_fantasia_imprompt, R.raw.rachmaninov_italian_polka,
        R.raw.rimsky_korsakov_flight_of_the_bumblebee, R.raw.sergei_prokofiev_dance_of_the_knights
    )
    var currentSongIndex = 0

        fun initPlayer()  {
            Log.d("Manager", "init Player")
        }

        fun destroy() {
            Log.d("Manager", "onDestroy Player")
        }
}