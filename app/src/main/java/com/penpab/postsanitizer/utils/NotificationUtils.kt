package com.penpab.postsanitizer.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.penpab.postsanitizer.R
import com.penpab.postsanitizer.api.WordpressApi
import com.penpab.postsanitizer.utils.Constants.BASE_URL
import com.penpab.postsanitizer.utils.Constants.CHANNEL_ID
import com.penpab.postsanitizer.utils.Constants.CHANNEL_NAME

object NotificationUtils {
    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannel(): NotificationChannel =
        NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)

    fun createNotification(context: Context): Notification {
        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("*** Fetching posts ***")
            .setContentText("GET request made to *** $BASE_URL ***")
            .build()
    }
}