package com.syafii.rxjavabasic.util

import java.text.SimpleDateFormat
import java.util.*

object StringUtils {
    fun dateToString(date: Date, format: String = "yyyy-MM-dd"): String {
        val formatter = SimpleDateFormat(format, Locale.getDefault())
        return formatter.format(date)
    }

    fun stringToDate(date: String, format: String = "yyyy-MM-dd"): Date{
        val formatter = SimpleDateFormat(format, Locale.getDefault())

        return formatter.parse(date)
    }
}