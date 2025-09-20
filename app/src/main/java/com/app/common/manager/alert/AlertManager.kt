package com.app.common.manager.alert

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.WindowManager
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AlertManager {
    companion object {
        fun createAlert(context: Context?, message: String, positiveText: String = "OK", completionPositive: (() -> Unit)? = null, negativeText: String? = null, completionNegative: (()-> Unit)? = null, cancelAble: Boolean = true) {
            try {
                context?.let {
                    val dialogBuilder = AlertDialog.Builder(it)
                    dialogBuilder.setMessage(message)

                    dialogBuilder.setCancelable(cancelAble)
                    positiveText?.let {
                        dialogBuilder.setPositiveButton(it) { _, _ ->
                            completionPositive?.invoke()
                        }
                    }

                    negativeText?.let {
                        dialogBuilder.setNegativeButton(it) { _, _ ->
                            completionNegative?.invoke()
                        }
                    }

                    val alert = dialogBuilder.create()
                    alert.show()
                }

            } catch (e: WindowManager.BadTokenException) {
                Log.d("====", e.toString())
            }
        }

        fun createToast(context: Context?, message: String) {
            GlobalScope.async(Dispatchers.Main) {
                try {
                    context?.let {
                        val toast = Toast.makeText(it,message, Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER, 0, 0)
                        toast.show()
                    }
                } catch (e: WindowManager.BadTokenException) {
                    Log.d("====", e.toString())
                }
            }
        }
    }
}
