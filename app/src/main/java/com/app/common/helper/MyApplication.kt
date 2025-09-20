package com.app.common.helper

import android.app.Application
import android.content.Context
import android.provider.Settings
import androidx.appcompat.app.AppCompatDelegate
import com.app.common.manager.keychain.KeychainManager

class MyApplication: Application() {
    companion object {
        private var context: Context? = null

        fun getAppContext(): Context? {
            return context
        }
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        context = applicationContext

        context?.let {
            val id = Settings.Secure.getString(context?.contentResolver, Settings.Secure.ANDROID_ID)
            KeychainManager.getInstance().setSharedPrefrerence(it, KeychainId.udid.name, id)
        }
    }
}
