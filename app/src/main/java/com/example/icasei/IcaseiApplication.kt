package com.example.icasei

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.example.icasei.presentation.localPush.LocalNotificationService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class IcaseiApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                LocalNotificationService.PUSH_CHANNEL_ID,
                "Video Push",
                NotificationManager.IMPORTANCE_DEFAULT,
            )
            channel.description = "Used for show videos notifications"

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
