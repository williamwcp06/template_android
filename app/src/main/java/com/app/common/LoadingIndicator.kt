package com.app.common

import android.app.Dialog
import android.content.Context
import com.app.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class LoadingIndicator {

    companion object {
        lateinit var context: Context
        lateinit var dialog: Dialog

        var isShow: Boolean = false

        fun createDialog(context: Context) {
            if (!isShow) {
                Companion.context = context
                GlobalScope.async(Dispatchers.Main) {
                    dialog = Dialog(context)
//                dialog.setCancelable(false)
                    dialog.setContentView(R.layout.loading_indicator)
                    dialog.show()
                    isShow = true
                }
            }
        }

        fun hide() {
            GlobalScope.async(Dispatchers.Main) {
                dialog.hide()
                isShow = false
            }
        }
    }
}
