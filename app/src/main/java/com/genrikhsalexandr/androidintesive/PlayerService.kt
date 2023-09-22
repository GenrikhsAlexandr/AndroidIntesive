package com.genrikhsalexandr.androidintesive

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService

private const val ACTION_PLAY: String = "com.genrikhsalexandr.androidintesive.action.PLAY"
private const val ACTION_STOP: String = "com.genrikhsalexandr.androidintesive.action.STOP"
private const val ACTION_PAUSE: String = "com.genrikhsalexandr.androidintesive.action.PAUSE"
private const val ACTION_NEXT: String = "com.genrikhsalexandr.androidintesive.action.NEXT"
private const val ACTION_PREVIOUS: String = "com.genrikhsalexandr.androidintesive.action.PAUSE"


class PlayerService : LifecycleService() {
    companion object {
        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "channel_name"
        private const val NOTIFICATION_ID = 1

        fun startServicePlayer(context: Context) {
            val startIntent = Intent(context, PlayerService::class.java)
            context.startForegroundService(startIntent)
            Log.d("PlayerService", "startService")
        }

        fun stopServicePlayer(context: Context) {
            val stopIntent = Intent(context, PlayerService::class.java)
            context.startForegroundService(stopIntent)
            Log.d("PlayerService", "startService")
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("PlayerService", "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        createNotificationChannel()
        createNotification()
        when(intent?.action) {
            ACTION_PLAY -> {if (PlayerManager.player!=null) {PlayerManager.player?.start()}}
            ACTION_PAUSE -> {PlayerManager.player?.pause()}
        }

        Log.d("PlayerService", "onStartCommand")
        return START_NOT_STICKY
    }

    private fun createNotificationChannel() {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel =
            NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(channel)
    }

    private fun createNotification() {
        val pendingIntent: PendingIntent =
            Intent(this, MainActivity::class.java).let { notificationIntent ->
                PendingIntent.getActivity(
                    this, 0, notificationIntent,
                    PendingIntent.FLAG_IMMUTABLE
                )
            }

        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Music Player")
            .setContentText("Playing Music")
            .setSmallIcon(R.drawable.ic_music_note)
            .setContentIntent(pendingIntent)
            .addAction(R.drawable.ic_play, "Play", pendingIntent)
            .addAction(R.drawable.ic_pause, "Pause", pendingIntent)
            .addAction(R.drawable.ic_stop, "Stop", pendingIntent)
            .addAction(R.drawable.ic_next, "Next", pendingIntent)
            .addAction(R.drawable.ic_previous, "Previous", pendingIntent)
            .build()
        startForeground(NOTIFICATION_ID, notification)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("PlayerService", "onDestroy")
    }
}