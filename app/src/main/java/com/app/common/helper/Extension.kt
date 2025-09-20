package com.app.common

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.app.common.helper.MyApplication
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*
import android.util.Base64

//private const val salt = "sm_attendance_id"

fun String.encode(): String? {
//    try {
////        val encodedString: String = Base64.encodeToString("${salt}_${this}".toByteArray(), 1)
//        val encodedString: String = Base64.encodeToString(this.toByteArray(), 1)
//        var base64String = ""
//        val letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
//        encodedString.forEach {
//            val random = letters.random().toString().first().toString()
//            base64String += "${it}${random}"
//        }
//        return base64String
//    } catch (e: Exception) {
//        return null
//    }
    return this
}

fun String.decode(): String? {
//    var base64String = ""
//    this.forEachIndexed { idx, str ->
//        if (idx % 2 == 0) {
//            base64String += str
//        }
//    }
//    try {
//        val decodedString = String(Base64.decode(base64String, 1), charset("UTF-8"))
////        val substring = decodedString.substringAfterLast("${salt}_")
//        return decodedString//substring
//    } catch (e: Exception) {
//        return null
//    }
    return this
}

fun Long.formatAsDate(format: String, timeZone: String = "Asia/Jakarta"): String {
    val formatter = SimpleDateFormat(format, Locale.getDefault())
    formatter.timeZone = TimeZone.getTimeZone(timeZone)
    return formatter.format(Date(this * 1000)) // Convert seconds to milliseconds
}

fun IntRange.random() =
    Random().nextInt((endInclusive + 1) - start) + start

fun ImageView.downloadImage(context: Context, imagePath: String, imageView: ImageView) {
    Glide.with(context)
        .asBitmap()
        .load(imagePath)
        .into(object : CustomTarget<Bitmap>(){
            override fun onResourceReady(resource: Bitmap, transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?) {
                imageView.setImageBitmap(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                // this is called when imageView is cleared on lifecycle call or for
                // some other reason.
                // if you are referencing the bitmap somewhere else too other than this imageView
                // clear it here as you can no longer have the bitmap
            }
        })
}

fun Bitmap.toBase64(): String {
    val baos = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 50, baos) // 100 is quality (0-100)
    val byteArray = baos.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT) ?: ""
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

@SuppressLint("UseCompatLoadingForDrawables", "DiscouragedApi")
fun Resources.getAseet(resources: Resources, name: String): Int {
    MyApplication.getAppContext()?.let {
        return resources.getIdentifier(name , "drawable", it.packageName)
    }
    return 0
}
