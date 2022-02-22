package com.example.jornadasorientate.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.jornadasorientate.utils.NotificationUtils

class AlarmBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        NotificationUtils(context).apply {
            val notification = getNotificationBuilder().build()
            getManager().notify(150, notification)
        }
    }
}
