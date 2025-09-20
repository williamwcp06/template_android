package com.app.common.manager.API

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.google.gson.GsonBuilder
import com.app.common.BaseModel
import com.app.common.UserConfig
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.app.common.helper.Constant
import com.app.common.helper.ErrorCode
import com.app.common.helper.KeychainId
import com.app.common.helper.MyApplication
import com.app.common.manager.keychain.KeychainManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

//public val appErrorCallback : (AppError) -> Unit = {}

class AppError(message:String): Exception(message)

interface ApiInterface {
//    @GET("api/karyawan/version/")
//    fun version(): Call<BaseModel<List<VersionModel>>>
//    @POST("api/karyawan/login/")
//    fun login(@Body model: HashMap<String, Any>): Call<BaseModel<LoginModel>>
//    @GET("api/karyawan/time_off/")
//    fun getTimeOff(@QueryMap model: HashMap<String, Any>): Call<BaseModel<ArrayList<TimeOffModel>>>


//    @DELETE("posts/{id}")
//    fun deletePost(@Path("id") id:Int): Call<String>

}

class ApiManager {

    companion object{
        @Volatile
        private var instance: ApiManager? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: ApiManager().also { instance = it }
        }

        val create = getInstance().getApiClient().create(ApiInterface::class.java)
    }
    private var retrofit:Retrofit?=null
    private val username: String = "alvin"
    private val password: String = "alvin"

    fun <T> makeApiCall(call: Call<BaseModel<T>>, completion: ((AppError?, BaseModel<T>?) -> Unit)? = null) {
//        if (UserConfig.getInstance().token.isNullOrBlank() && call.request().url.toString() != create.login(hashMapOf()).request().url.toString()  && call.request().url.toString() != create.version().request().url.toString()) {
//            forceLogout()
//            completion?.invoke(null, null)
//            return
//        }
        call.enqueue(object : Callback<BaseModel<T>> {
            override fun onResponse(call: Call<BaseModel<T>>, response: Response<BaseModel<T>>) {
                val res = response.body()

                if (res?.info?.type == APIResponseType.Error.value || res?.data == null || response.code() != 200) {
                    val errorMessage = res?.info?.message ?: "[${response.code()}] Please try again!"
                    completion?.invoke(AppError(errorMessage), null)
                    return
                }

                if (res?.info?.status_code == ErrorCode.tokenInvalid.value) {
//                    val errorMessage = res.info?.message ?: "[${ErrorCode.tokenInvalid}] Please try again!"
                    forceLogout()
                    completion?.invoke(null, null)
                    return
                }

                completion?.invoke(null, res)
            }

            override fun onFailure(call: Call<BaseModel<T>>, t: Throwable) {
                completion?.invoke(AppError(t.message ?: "Unknown error"), null)
            }
        })
    }

    private fun getApiClient(): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(BasicAuthInterceptor(username,password))

        MyApplication.getAppContext()?.let {
            okHttpClient.addInterceptor(chuckCollector(it))
        }

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(Constant.baseUrl)
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        return retrofit!!
    }

//    fun httpLogging(): HttpLoggingInterceptor {
//        val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//        return httpLoggingInterceptor
//    }

    private fun chuckCollector(context: Context): ChuckerInterceptor {
        val chuckerCollector = ChuckerCollector(
            context = context,
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )

        val chuckerInterceptor = ChuckerInterceptor.Builder(context)
            .collector(chuckerCollector)
            .maxContentLength(250_000L)
//            .redactHeaders("Auth-Token", "Bearer")
            .alwaysReadResponseBody(true)
            .build()

        return chuckerInterceptor
    }

    private fun forceLogout() {
        MyApplication.getAppContext()?.let {
//            if (it !is Application) {
                UserConfig.getInstance().handleInvalidToken(it)
//            }
        }
    }
}

class BasicAuthInterceptor(username: String, password: String): Interceptor {
    private var credentials: String = Credentials.basic(username, password)

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", credentials).build()
        request = request.newBuilder().header("Content-Type", "text/plain").build()
        MyApplication.getAppContext()?.let {
            val id = KeychainManager.getInstance().getSharedPrefrerence(it, KeychainId.udid.name) as? String ?: ""
            request = request.newBuilder().header("device_id", id).build()
        }
        request = request.newBuilder().header("device_os", "android").build()
        request = request.newBuilder().header("os_version", Constant.systemVersion).build()
        request = request.newBuilder().header("app_build", Constant.buildCode.toString()).build()
        request = request.newBuilder().header("app_version", Constant.appsVersion).build()
        UserConfig.getInstance().token?.let {
            request = request.newBuilder().header("token", it).build()
        }
        return chain.proceed(request)
    }
}

enum class APIResponseType(val value: String) {
    Error("error"),
    Success("success");

    companion object {
        public fun fromValue(value: String): APIResponseType = when (value) {
            "error" -> Error
            "success"  -> Success
            else         -> throw IllegalArgumentException()
        }
    }
}

sealed class ApiResult<out T> {
    data class Result<T>(val response: T, val error: AppError?) : ApiResult<T>()
    data class Success<T>(val response: T) : ApiResult<T>()
    data class Error(val message: String) : ApiResult<Nothing>()
}
