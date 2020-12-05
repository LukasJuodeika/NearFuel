package com.ktu.nearfuel.network

import android.content.Context
import android.content.SharedPreferences

class AuthRepository(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(AuthRepository::class.simpleName, Context.MODE_PRIVATE)

    fun getAccessToken(): String {
        return sharedPreferences.getString(
            KEY_ACCESS_TOKEN,
            ""
        )!!
    }

    fun setAccessToken(token: String) {
        sharedPreferences.edit()
            .putString(KEY_ACCESS_TOKEN, token).apply()
    }

    fun getRefreshToken(): String {
        return sharedPreferences.getString(
            KEY_REFRESH_TOKEN,
            ""
        )!!
    }

    fun setRefreshToken(refreshToken: String) {
        sharedPreferences.edit()
            .putString(KEY_REFRESH_TOKEN, refreshToken)
            .apply()
    }

    companion object {
        private const val KEY_ACCESS_TOKEN = "auth.token"
        private const val KEY_REFRESH_TOKEN = "auth.refreshToken"
    }
}
