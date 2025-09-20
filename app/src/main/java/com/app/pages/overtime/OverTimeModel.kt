package com.app.pages.menu.overtime

import android.graphics.Color
import androidx.core.graphics.toColorInt
import com.app.common.BaseInfoModel
import com.app.common.BasePaginationModel

data class OverTimeResponseModel (
    var data: ArrayList<OverTimeModel> = arrayListOf(),
    var info: BaseInfoModel? = null,
    var pagination: BasePaginationModel? = null
)

data class OverTimeModel(
    var id: Int = -1,
    var karyawan_id: Int = -1,
    var karyawan_nama: String = "",
    var tgl: String = "",
    var durasi_jam: Int = -1,
    var keterangan: String = "",
    var status: InboxStatus = InboxStatus.pending,
    var status_by_id: Int = -1,
    var status_by: String = ""
)

enum class InboxStatus() {
  pending, approved, rejected;

  fun getColor(): Int {
    return when (this) {
      pending -> "#FFA500".toColorInt()        // You can change this to any color you like
      approved -> "#6488EA".toColorInt()     // Color for approved status
      rejected -> Color.RED       // Color for rejected status
    }
  }

  companion object {
    public fun fromValue(value: String): InboxStatus = when (value) {
      "pending" -> pending
      "approved"  -> approved
      "rejected"  -> rejected
      else         -> pending
    }
  }
}
