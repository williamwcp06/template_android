package com.app.pages.menu.overtime

import com.app.common.base.BaseViewModel
import com.app.common.helper.Constant
import com.app.common.manager.API.ApiManager

class OverTimeViewModel: BaseViewModel() {
    private var requesting: Boolean = false
    var overTime: OverTimeResponseModel = OverTimeResponseModel()

    fun getTimeOffList(completion: (() -> Unit)? = null) {
        val params: HashMap<String, Any> = hashMapOf()
        params["page"] = (overTime.pagination?.page ?: 0) + 1
        params["size"] = Constant.sizeForPage
        params["pagination"] = "active"
        requesting = true
//        ApiManager.getInstance().makeApiCall(ApiManager.create.getOverTime(params)) { error, res ->
//            overTime.pagination = res?.pagination
//            res?.data?.let {
//                overTime.data += it
//            }
//            completionError?.invoke(error)
//            completionReloadData?.invoke()
//            requesting = false
//        }
    }

    fun flushData() {
        requesting = false
        overTime.data = arrayListOf()
        overTime.pagination = null
    }

    fun canLoadMore(): Boolean {
        return ((overTime.pagination?.page ?: 0) * (overTime.pagination?.size ?: Constant.sizeForPage) < (overTime.pagination?.total ?: 0) && overTime.pagination != null && !requesting)
    }
}
