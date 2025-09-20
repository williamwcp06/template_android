package com.app.common.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.common.manager.API.ApiResult
import com.app.common.manager.API.AppError
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseViewModel: ViewModel() {
    var completionReloadData: () -> Unit = {}
    var completionError: (AppError?) -> Unit = {}
}
