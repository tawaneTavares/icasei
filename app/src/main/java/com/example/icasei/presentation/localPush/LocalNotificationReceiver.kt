package com.example.icasei.presentation.localPush

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class LocalNotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val service = LocalNotificationService(context)
//        service.showNotification(NotificationData)
    }
}
