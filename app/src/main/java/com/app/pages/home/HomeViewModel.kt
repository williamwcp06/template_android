package com.app.pages.home

//class HomeViewModel: BaseViewModel() {
//  var notification: NotificationResponseModel = NotificationResponseModel()
//
//  fun getOperationalTime(completion: (() -> Unit)? = null) {
//    ApiManager.getInstance().makeApiCall(ApiManager.create.getOperationalTime()) { error, res ->
//      res?.data?.let {
//        UserConfig.getInstance().operationalTimeList = it
//        completion?.invoke()
//      }
//      completionError?.invoke(error)
//    }
//  }
//
//
//  fun getNotification(completion: (() -> Unit)? = null) {
//    val params: HashMap<String, Any> = hashMapOf()
////        params["karyawan_id"] = UserConfig.getInstance().data.id
//    params["page"] = 1
//    params["size"] = Constant.sizeForPage
//    params["pagination"] = "active"
//    ApiManager.getInstance().makeApiCall(ApiManager.create.getNotification(params)) { error, res ->
//      res?.data?.let {
//        notification.data = it
//        completion?.invoke()
//      }
//      completionError?.invoke(error)
//    }
//  }
//}
