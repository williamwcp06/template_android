package com.app.common.bottomSheet//package com.app.common.bottomSheet
//
//import android.app.Activity
//import androidx.recyclerview.widget.DefaultItemAnimator
//import androidx.recyclerview.widget.DividerItemDecoration
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.app.R
//import com.app.common.adapter.BasicListAdapter
//import com.google.android.material.bottomsheet.BottomSheetDialog
//
//open class BottomSheetRecycle(activity: Activity, val data: List<String>): BottomSheet(activity) {
//
//    var completion: ((Int) -> Unit)? = null
//
//    init {
//        val view = activity.layoutInflater.inflate(R.layout.bottom_view_recycle,null)
//        val recycle = view.findViewById<RecyclerView>(R.id.bottom_view_recycle)
//        recycle.itemAnimator = DefaultItemAnimator()
//        recycle.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
//        val adapter = BasicListAdapter { index ->
//            completion?.invoke(index)
//        }
//        recycle.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
//        recycle.adapter = adapter
//        adapter.setData(data)
//        setView(view)
//    }
//}
