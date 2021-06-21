package com.syafii.rxjavabasic.util.db

import android.content.Context
import android.content.SharedPreferences

/*
 * Created by Muhamad Syafii
 * Friday, 18/06/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */
class SharedPreference(context: Context) {
    private val PREF_MODE = 0
    private val PREF_NAME = "rxjava-database"

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, PREF_MODE)
    val editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun saveString(key: String, value: String) {
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String): String? {
        return sharedPreferences.getString(key, "")
    }

    fun saveInt(key: String, value: Int) {
        editor.putInt(key, value)
        editor.apply()
    }

    fun getInt(key: String): Int? {
        return sharedPreferences.getInt(key, 0)
    }

    fun saveBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(key: String): Boolean? {
        return sharedPreferences.getBoolean(key, false)
    }

    fun saveLong(key: String, value: Long) {
        editor.putLong(key, value)
        editor.apply()
    }

    fun getLong(key: String): Long {
        return sharedPreferences.getLong(key, 0L)
    }

    fun clearPreference() {
        sharedPreferences.edit()
            .clear()
            .apply()
    }
}