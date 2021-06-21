package com.syafii.rxjavabasic.util
/*
 * Created by Muhamad Syafii
 * Friday, 18/06/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber
import java.util.*

class LogErrorFireStore(
    private val context: Context,
    private val db: FirebaseFirestore
) {

    fun sendLogs(tag: String, error: String) {
        val data: HashMap<String, Any> = hashMapOf(
            "tag" to tag,
            "error" to error,
            "time" to StringUtils.stringToDate(StringUtils.dateToString(Calendar.getInstance().time)),
            "device" to DeviceInfo.getDeviceName(context),
            "version" to DeviceInfo.versionApp(context)
        )
        db.collection("log-errors")
            .add(data)
            .addOnSuccessListener {
                Timber.tag("firestore-log").d("on success document created : ${it.id}")
            }.addOnFailureListener {
                Timber.tag("firestore-log").d("error : ${it.localizedMessage}")
            }
    }
}
