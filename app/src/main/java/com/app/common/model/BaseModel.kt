package com.app.common

import com.app.common.manager.API.APIResponseType
 class BaseModel<T>(
    var info: BaseInfoModel? = null,
    var pagination: BasePaginationModel? = null,
    var data: T? = null
)

class BaseInfoModel(
    var message: String = "",
    var type: String = APIResponseType.Error.value,
    var status_code: Int? = null
)

class BasePaginationModel(
    var page: Int = -1,
    var size: Int = -1,
    var total: Int = -1
)


//data class BasicRequestModel(
//    var action: String = ActionRequestType.get.name
//
//)
