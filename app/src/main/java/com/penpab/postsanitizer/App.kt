package com.penpab.postsanitizer

import android.app.Application
import android.app.NotificationManager
import android.os.Build
import androidx.work.Configuration
import com.penpab.postsanitizer.utils.NotificationUtils

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationUtils.createNotificationChannel().also { channel ->
                getSystemService(NotificationManager::class.java)
                    .createNotificationChannel(channel)
            }
        }
    }
}