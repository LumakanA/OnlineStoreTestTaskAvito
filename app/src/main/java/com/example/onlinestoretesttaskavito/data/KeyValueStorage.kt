package com.example.onlinestoretesttaskavito.data

import android.content.Context


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
            return value
        }
        set(value) {
            putString(KeyAccessToken, value)
        }

    var refreshToken: String?
        get() {
            val value = getString(KeyRefreshToken)
            return value
        }
        set(value) {
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