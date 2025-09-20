package com.app.common.bottomSheet

import android.app.Activity
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.R
//import com.app.common.adapter.BasicListAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog

open class BottomSheet(val activity: Activity) {

    private var dialog: BottomSheetDialog = BottomSheetDialog(activity)

    fun setView(view: View) {
        dialog.setContentView(view)
    }

    fun show() {
        dialog.show()
    }

    fun hide() {
        dialog.hide()
    }
}
