package com.example.jornadasorientate.utils

import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.jornadasorientate.ui.MainActivity

class NotificationUtils(context: Context) : ContextWrapper(context) {

    companion object {
        private const val CHANNEL_ID = "Alert Notification ID"
        private const val CHANNEL_NAME = "Alert Notification"
    }

    private var manager: NotificationManager? = null

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannels()
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun createChannels() {
        val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
        channel.enableVibration(true)

        getManager().createNotificationChannel(channel)
    }

    fun getManager(): NotificationManager {
        if (manager == null) manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        return manager as NotificationManager
    }

    fun getNotificationBuilder(): NotificationCompat.Builder {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        return NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setContentTitle("Alarm!")
            .setContentText("Your Alarm is working.")
            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
            .setColor(Color.YELLOW)
            .setContentIntent(pendingIntent)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE))
            .setAutoCancel(true)
    }
}
