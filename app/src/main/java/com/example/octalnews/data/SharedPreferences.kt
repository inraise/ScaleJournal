package com.example.octalnews.data

import android.app.Activity
import android.content.Context

fun SaveData(data: String, key: String, context: Activity) {
    val sharedPref = context.getPreferences(Context.MODE_PRIVATE) ?: return
    with (sharedPref.edit()) {
        putString(key, data)
        apply()
    }
}


fun ReadData(key: String, context: Activity): String {
    val sharedPref = context.getPreferences(Context.MODE_PRIVATE)
    return sharedPref.getString(key, null).toString()
}
