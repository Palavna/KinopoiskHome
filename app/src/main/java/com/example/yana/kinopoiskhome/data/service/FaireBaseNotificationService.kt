package com.example.yana.kinopoiskhome.data.service

import android.app.Notification
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
import kotlin.math.log

class FaireBaseNotificationService: FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.d("fghfgh", "fghfgh")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        createNotification(applicationContext, message)
    }


    fun createNotification(context: Context, message: RemoteMessage) {
        createNotificationChannel(context)

        val taskStackBuilder = TaskStackBuilder.create(context)
        taskStackBuilder.addParentStack(MainActivity::class.java)
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("name", message.data["desc"])
        taskStackBuilder.addNextIntentWithParentStack(intent)

        val builder = NotificationCompat.Builder(context, Companion.CHANNEL_ID)
            .setSmallIcon(R.drawable.film_reel)
            .setContentTitle("Здравствуйте")
            .setContentText("Самое время посмотреть хороший фильм!")
            .setAutoCancel(false)
            .setContentIntent(taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        NotificationManagerCompat.from(this).apply {
            notify(1, builder.build())
        }

    }


    private fun createNotificationChannel(context: Context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = context.getString(R.string.channel_name)
            val descriptionText = context.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(Companion.CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {
        private const val CHANNEL_ID = "CHANNEL_ID"
    }
}