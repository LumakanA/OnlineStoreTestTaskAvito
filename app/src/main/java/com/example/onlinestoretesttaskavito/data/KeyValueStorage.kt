package com.example.onlinestoretesttaskavito.data

import android.content.Context
import android.util.Log


private const val SharedPreferencesDefaultName = "app_preferences"

private const val KeyAccessToken = "access_token"
private const val KeyRefreshToken = "refresh_token"

class KeyValueStorage(
    context: Context
) {
    private val prefs = context.getSharedPreferences(
        SharedPreferencesDefaultName,
        Context.MODE_PRIVATE
    )

    var accessToken: String?
        get() {
            val value = getString(KeyAccessToken)
            Log.d("qwerty", "Retrieved access token: $value")
            return value
        }
        set(value) {
            Log.d("qwerty", "Setting access token: $value")
            putString(KeyAccessToken, value)
        }

    var refreshToken: String?
        get() {
            val value = getString(KeyRefreshToken)
            Log.d("qwerty", "Retrieved refresh token: $value")
            return value
        }
        set(value) {
            Log.d("qwerty", "Setting refresh token: $value")
            putString(KeyRefreshToken, value)
        }

    private fun getString(key: String): String? {
        return prefs.getString(key, null)
    }

    private fun putString(key: String, value: String?) {
        prefs.edit()
            .putString(key, value)
            .apply()
    }
}