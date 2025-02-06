package com.example.icasei.presentation.localPush

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.net.toUri
import com.example.icasei.R
import com.example.icasei.common.Constants.DEEPLINK_DOMAIN
import com.example.icasei.presentation.MainActivity

class LocalNotificationService(
    private val context: Context,
) {
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(notificationContent: NotificationData) {
        val activityIntent = Intent(context, MainActivity::class.java).apply {
            data = ("https://$DEEPLINK_DOMAIN/?videoID=" + notificationContent.videoID).toUri()
        }

        val pendingIntent = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(activityIntent)
            getPendingIntent(0, PendingIntent.FLAG_IMMUTABLE)
        }
        val notification = NotificationCompat.Builder(context, PUSH_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_logo_casar)
            .setContentTitle("Olhe esse vídeo!")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(1, notification)
    }

    companion object {
        const val PUSH_CHANNEL_ID = "push_channel"
    }
}
