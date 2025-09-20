package com.app.common.helper

import android.os.Build
import com.app.BuildConfig

class Constant {
    companion object {
        const val baseUrl = "https://tigapilarrenjana.org/motor/"

        const val sizeForPage = 5

        val systemVersion: String = Build.VERSION.RELEASE
        const val appsVersion = BuildConfig.VERSION_NAME
        const val buildCode = BuildConfig.VERSION_CODE

        val colors: MutableList<String> = mutableListOf("#7cc8ea", "#f0b9e0", "#e2e767", "#f86d56", "#f59267", "#f56ca0", "#f5cd6d", "#6ac7b5", "#e0f56c", "#6f6cb1")
//        val classBackground: HashMap<String, Int> = hashMapOf("001" to R.drawable.img_background_sm, "002" to R.drawable.img_background_lb, "003" to R.drawable.img_background_jg, "004" to R.drawable.img_background_kk)
        fun getUpdateURL(): String {
            val code = appsVersion.split(".").toTypedArray().last().toInt()
            return "https://google.com"
        }
    }
}

enum class AppsStatus {
    available, update, obsolete
}

enum class ActionRequestType {
    update, insert, get, delete, transfer
}

enum class Month {
    All, January, February, March, April, May, June, July, Augustus, September, October, November, December;

    fun description(): String {
        when (this) {
            All -> return ""
            January -> return "01"
            February -> return "02"
            March -> return "03"
            April -> return "04"
            May -> return "05"
            June -> return "06"
            July -> return "07"
            Augustus -> return "08"
            September -> return "09"
            October -> return "10"
            November -> return "11"
            December -> return "12"

            else -> { return "" }
        }
    }
}

enum class StoreDataKey {
    username, password, serviceId
}

enum class HomeMenu {
    checkIn, checkOut, updatePoint, redeemSouvenir, writePickUpCard, register;

    fun description(): String {
        when (this) {
            checkIn -> return "Check In"
            checkOut -> return "Check Out"
            updatePoint -> return "Update Point"
            redeemSouvenir -> return "Redeem Souvenir"
            writePickUpCard -> return "Write Pick Up Card"
            register -> return "Register"

            else -> { return "" }
        }
    }
}

enum class ReportMenu {
    attendance, pickUpCard, redeemSouvenir;

    fun description(): String {
        when (this) {
            attendance -> return "Attendance"
            pickUpCard -> return "Pick Up Card"
            redeemSouvenir -> return "Redeem Souvenir"

            else -> { return "" }
        }
    }
}

enum class RedeemPointType {
    regular, discount
}

enum class ActionType {
    update, app
}

enum class Gender {
    pria, wanita
}

enum class Role {
    teacher, student, parent, guest;

    fun id(): Int {
        when (this) {
            teacher -> return 1
            student -> return 2
            parent -> return 3
            guest -> return 4

            else -> { return -1 }
        }
    }
}

enum class ErrorCode(val value: Int) {
    connection(-1),
    decodeData(-2),
    errorScan(-3),
    errorAuth(-4),
    emptyIdCard(-5),
    locationAccess(-6),
    emptyLocation(-7),
    tokenInvalid(401)
}

enum class KeychainId {
    token, udid
}



