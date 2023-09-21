package com.genrikhsalexandr.androidintesive

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.LifecycleService

class PlayerService : LifecycleService() {
    companion object {
        private const val CHANNEL_ID = "channel_id"
        private const val NOTIFICATION_ID = 1

        fun startService(context: Context) {
            val startIntent = Intent(context, PlayerService::class.java)
            context.startForegroundService(startIntent)
            Log.d("PlayerService", "startService")
        }

    }
    override fun onCreate() {
        super.onCreate()
        Log.d("PlayerService", "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        val pendingIntent: PendingIntent =
            Intent(this, PlayerService::class.java).let { notificationIntent ->
                PendingIntent.getService(this, 0, notificationIntent,
                    PendingIntent.FLAG_IMMUTABLE)
            }

        val notification: Notification = Notification.Builder(this, CHANNEL_ID)
            .setContentTitle("Music Player")
            .setContentText("Playing Music")
            .setSmallIcon(R.drawable.ic_music_note)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(NOTIFICATION_ID, notification)
        Log.d("PlayerService", "onStartCommand")

        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("PlayerService", "onDestroy")
    }
}



