package com.syafii.rxjavabasic.util

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import java.lang.reflect.Field
import java.util.*

/*
 * Created by Muhamad Syafii
 * Monday, 20/06/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */

object DeviceInfo {

    fun getDeviceOs(): String {
        val fields: Array<Field> = Build.VERSION_CODES::class.java.fields
        var mDeviceOsName = ""

        run loop@{
            fields.forEach {
                if (it.getInt(Any()) == Build.VERSION.SDK_INT) {
                    mDeviceOsName = it.name
                    return@loop
                }
            }
        }

        return mDeviceOsName
    }

    fun getDeviceName(context: Context): String {
        return (Build.MODEL ?: "Unknown") + " or " + Settings.Secure.getString(
            context.contentResolver,
            "bluetooth_name"
        )
    }

    fun generateUUID(): String {
        return UUID.randomUUID().toString()
    }

    fun versionApp(context: Context): String {
        return try {
            context.packageManager.getPackageInfo(context.packageName, 0).versionName
        } catch (e: PackageManager.NameNotFoundException) {
            "Version Unknown"
        }
    }
}