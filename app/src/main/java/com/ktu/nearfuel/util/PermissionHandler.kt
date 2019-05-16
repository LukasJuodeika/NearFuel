package com.ktu.nearfuel.util

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build

class PermissionHandler{

    companion object {
        fun getPermissions(activity: Activity) {
            val notGranted: ArrayList<String> =
                getMissingPermissions(activity)
            val array = arrayOfNulls<String>(notGranted.size)
            notGranted.toArray(array)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && array.isNotEmpty()) {
                activity.requestPermissions(array,
                    PERMISSION_GENERAL
                )
            }
        }

        fun getMissingPermissions(activity: Activity): ArrayList<String> {
            val notGranted = ArrayList<String>()
            for (permission in permissions) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                        notGranted.add(permission)
                    }
                }
            }
            return notGranted
        }

        private const val PERMISSION_GENERAL = 10
        private val permissions = arrayOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION)

    }
}