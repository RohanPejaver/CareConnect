package com.example.careconnect.core

import android.content.Context
import android.util.Log
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import com.example.careconnect.core.Constants.TAG
import com.github.marlonlom.utilities.timeago.TimeAgo
import com.example.careconnect.BuildConfig
import android.os.Build


class Utils {
    companion object {
        fun print(e: Exception) = Log.e(TAG, e.stackTraceToString())

        fun showMessage(
            context: Context,
            message: String?
        ) = makeText(context, message, LENGTH_LONG).show()
    }
}

object Utility {
    fun currentTimeStamp(): Long {
        return System.currentTimeMillis()
    }

    fun applicationVersion(): String {
        return "${BuildConfig.VERSION_NAME}:${BuildConfig.VERSION_CODE}"
    }

    fun getDeviceId(): String {
        return Build.ID
    }

    fun deviceModel(): String {
        return "${Build.MODEL} ${Build.BRAND} ${Build.DEVICE}"
    }

    fun systemOS(): String {
        return "${Build.ID} ${Build.VERSION.SDK_INT} ${Build.VERSION.CODENAME}"
    }

    fun getTimeAgo(time: Long): String {
        return TimeAgo.using(time = time)
    }
}