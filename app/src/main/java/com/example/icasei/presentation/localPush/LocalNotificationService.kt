package com.example.icasei.presentation.localPush

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.icasei.R
import com.example.icasei.presentation.MainActivity

class LocalNotificationService(
    private val context: Context,
) {
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(notificationContent: NotificationData) {
        val activityIntent = Intent(context, MainActivity::class.java).setAction(Intent.ACTION_VIEW)
        activityIntent.putExtra("videoUrl", notificationContent.videoUrl)

        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            PendingIntent.FLAG_IMMUTABLE,
        )
        val pushIntent = PendingIntent.getBroadcast(
            context,
            2,
            Intent(context, LocalNotificationReceiver::class.java),
            PendingIntent.FLAG_IMMUTABLE,
        )
        val notification = NotificationCompat.Builder(context, PUSH_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_logo_casar)
            .setContentTitle("Olhe esse vídeo!")
            .setContentIntent(activityPendingIntent)
            .addAction(
                R.drawable.ic_logo_casar,
                "local",
                pushIntent,
            )
            .build()

        notificationManager.notify(1, notification)
    }

    companion object {
        const val PUSH_CHANNEL_ID = "push_channel"
    }
}
