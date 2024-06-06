package com.userslistapp.data.utils

import android.util.Log
import com.userslistapp.data.BuildConfig

class DataLogger {

    companion object {
        fun log(function: String, message: String, tag: String, userId: String = "") {
            if (BuildConfig.DEBUG) {
                Log.e(tag, message)
            }
        }

        fun error(function: String, message: String, tag: String, userId: String = "") {
            if (BuildConfig.DEBUG) {
                Log.e(tag, message)
            }
        }

        fun exception(function: String, exception: Exception, tag: String, userId: String = "") {
            if (BuildConfig.DEBUG) {
                Log.e(tag, "${exception.printStackTrace()}")
            }
        }

        fun exception(function: String, exception: Exception, message: String, tag: String, userId: String = "") {
            if (BuildConfig.DEBUG) {
                Log.e(tag, "${exception.printStackTrace()}")
            }
        }
    }

}