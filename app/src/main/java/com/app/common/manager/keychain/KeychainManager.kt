package com.app.common.manager.keychain

import android.content.Context

class KeychainManager {

    companion object{
        var keyAction = "user_prefs"
        @Volatile
        private var instance: KeychainManager? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: KeychainManager().also { instance = it }
        }

    }

    fun getSharedPrefrerence(context: Context, key: String): Any? {
        val sharedPreferences = context.getSharedPreferences(keyAction, Context.MODE_PRIVATE)
        return sharedPreferences.all[key]
    }

    fun setSharedPrefrerence(context: Context, key: String, value: Any) {
        val sharedPreferences = context.getSharedPreferences(keyAction, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Store value based on its type
        when (value) {
            is String -> editor.putString(key, value)
            is Int -> editor.putInt(key, value)
            is Boolean -> editor.putBoolean(key, value)
            is Float -> editor.putFloat(key, value)
            is Long -> editor.putLong(key, value)
            else -> throw IllegalArgumentException("This type cannot be saved into SharedPreferences")
        }

        editor.apply()
    }

    fun removeSharedPrefrerence(context: Context, key: String) {
        val sharedPreferences = context.getSharedPreferences(keyAction, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove(key)
        editor.apply()
    }
}
