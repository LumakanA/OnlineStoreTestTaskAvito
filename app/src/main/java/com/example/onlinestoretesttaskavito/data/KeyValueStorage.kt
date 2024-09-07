package com.example.onlinestoretesttaskavito.data

import android.content.Context


private const val SharedPreferencesDefaultName = "app_preferences"

class KeyValueStorage(
    context: Context
) {
    private val prefs = context.getSharedPreferences(
        SharedPreferencesDefaultName,
        Context.MODE_PRIVATE
    )

    private fun getString(key: String): String? {
        return prefs.getString(key, null)
    }

    private fun putString(key: String, value: String?) {
        prefs.edit()
            .putString(key, value)
            .apply()
    }
}