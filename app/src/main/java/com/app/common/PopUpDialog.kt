package com.app.common

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog

open class PopUpDialog(val activity: Activity) {
    lateinit var dialog: AlertDialog
    lateinit var view: View
    var onDismissListener: (() -> Unit)? = null
    init {
        initDialog(null)
    }
    open fun dismiss() {
        dialog.dismiss()
    }
    open fun initDialog(layoutId: Int? = null) {
        if (layoutId != null) {
//            val selectedStyleId = styleId ?: R.style.TransparentDialog
            val inflater = LayoutInflater.from(activity)
            val builder = AlertDialog.Builder(activity)
            view = inflater.inflate(layoutId, null)
            builder.setView(view)
            dialog = builder.create()
//            dialog.window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                    or View.SYSTEM_UI_FLAG_FULLSCREEN
//                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            dialog.setOnDismissListener {
                onDismissListener?.invoke()
            }
        }
    }
}
