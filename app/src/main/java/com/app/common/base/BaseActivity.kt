package com.app.common.base

import android.Manifest
import android.R
import android.content.pm.PackageManager
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.app.common.UserConfig


open class BaseActivity: AppCompatActivity() {

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Izin diberikan, Chucker akan muncul di notifikasi
            Toast.makeText(this, "Notifikasi Chucker Aktif", Toast.LENGTH_SHORT).show()
        } else {
            // Izin ditolak, beri tahu user bahwa log tidak akan terlihat di notifikasi
            Toast.makeText(this, "Izin ditolak, Chucker tidak bisa muncul", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        loadData()
        initBinding()
        initUI()
        setData()
//        MyApplication.getInstance().activity = this
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            checkAndRequestNotificationPermission()
        }
    }

    override fun onResume() {
        super.onResume()
//        MyApplication.getInstance().activity = this
    }

    open fun initBinding() {}

    open fun loadData() {}

    open fun setData() {
        //if (UserConfig.getInstance().token.isNullOrBlank()) {
        //    finish()
        //}
    }

    open fun initUI() {}

    open fun changeBackgroundImage(code: Int) {
        val rootView = window.decorView.findViewById<View>(R.id.content)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(0xffffff))
    }

    open fun disableBackGesture(rootView: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            rootView.setOnApplyWindowInsetsListener { v, insets ->
                // Define a rect that covers the left and right edges
                val height = resources.displayMetrics.heightPixels
                val edgeWidth = 100 // in pixels; increase if needed

                val leftEdge = Rect(0, 0, edgeWidth, height)
                val rightEdge = Rect(
                    resources.displayMetrics.widthPixels - edgeWidth,
                    0,
                    resources.displayMetrics.widthPixels,
                    height
                )

                v.systemGestureExclusionRects = listOf(leftEdge, rightEdge)

                insets
            }
        }
    }

    private fun checkAndRequestNotificationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            // Minta izin ke user
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    fun View.applyRootInsets(left: Int = 0, right: Int = 0, top: Int = 0, bottom: Int = 0) {
        ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left + left, bars.top + top, bars.right + right, bars.bottom + bottom)
            insets
        }
    }

    fun View.applyTopInsets(left: Int = 0, right: Int = 0, bottom: Int = 0, top: Int = 0) {
        ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
            val status = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
            v.setPadding(v.paddingLeft + left, status + top, v.paddingRight +  right, v.paddingBottom + bottom)
            insets
        }
    }

    fun View.applyBottomMarginInsets(left: Int = 0, right: Int = 0, top: Int = 0, bottom: Int = 0) {
        ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
            val nav = insets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars()).top
            val params = v.layoutParams as ViewGroup.MarginLayoutParams
            params.bottomMargin = nav + bottom
            params.topMargin = bars + top
            params.rightMargin = right
            params.leftMargin = left
            v.layoutParams = params
            insets
        }
    }


}

