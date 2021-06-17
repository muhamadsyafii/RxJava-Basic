package com.syafii.rxjavabasic.util

import android.os.Build
import com.google.firebase.firestore.FirebaseFirestore
import com.syafii.rxjavabasic.BuildConfig
import timber.log.Timber
import java.util.*

class LogErrorFireStore(
    private val db: FirebaseFirestore
) {

    fun sendLogs(tag: String, error: String) {
        val data: HashMap<String, Any> = hashMapOf(
            "tag" to tag,
            "error" to error,
            "time" to StringUtils.stringToDate(StringUtils.dateToString(Calendar.getInstance().time)),
            "device" to Build.DEVICE,
            "version" to BuildConfig.VERSION_CODE
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
