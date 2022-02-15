package com.example.yana.kinopoiskhome.data.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import com.example.yana.kinopoiskhome.R
import com.example.yana.kinopoiskhome.ui.main.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FaireBaseNotificationService: FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.d("TAG", "Refreshed token: $p0")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d("TAG", "Refreshed token: $message")
        createNotification(message)
    }

    fun createNotification( message: RemoteMessage) {
        createNotificationChannel()

        val taskStackBuilder = TaskStackBuilder.create(this)
        taskStackBuilder.addParentStack(MainActivity::class.java)
//        val intent = Intent(this, MainActivity::class.java)
//        intent.putExtra("name", message.data["desc"])
        taskStackBuilder.addNextIntentWithParentStack(Intent(this, MainActivity::class.java))

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.film_reel)
            .setContentTitle(message.notification?.title)
            .setContentText(message.notification?.body)
            .setAutoCancel(true)
            .setContentIntent(taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT))
            .build()
        with(NotificationManagerCompat.from(this)){
            notify(1, builder)
        }

    }

    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {
        const val CHANNEL_ID = "CHANNEL_ID"
    }
}