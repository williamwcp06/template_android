package com.app.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.app.common.helper.KeychainId
import com.app.common.helper.MyApplication
import com.app.common.manager.keychain.KeychainManager

class UserConfig {
  companion object {
    @Volatile
    private var instance: UserConfig? = null

    fun getInstance() = instance ?: synchronized(this) {
      instance ?: UserConfig().also { instance = it }
    }
  }


  var token: String? = MyApplication.Companion.getAppContext()?.let{
    KeychainManager.Companion.getInstance().getSharedPrefrerence(it, KeychainId.token.name) as? String
  }


  fun handleInvalidToken(context: Context) {
//        AlertManager.createAlert(context,"Token invalid, please re-login!", completionPositive = {
//
//        })
//    val intent = Intent(context, LoginActivity::class.java).apply {
//      Intent.setFlags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//    }
//    context.startActivity(intent)

    // You cannot finish an activity from here unless you know it's an Activity
//    if (context is Activity) {
//      context.finish()
//    }
  }
}
