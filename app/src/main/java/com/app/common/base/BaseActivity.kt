package com.app.common.base

import android.R
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.app.common.UserConfig


open class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        loadData()
        initBinding()
        initUI()
        setData()
    }

    open fun initBinding() {}

    open fun loadData() {}

    open fun setData() {
        if (UserConfig.getInstance().token.isNullOrBlank()) {
            finish()
        }
    }

    open fun initUI() {}

    open fun changeBackgroundImage(code: Int) {
        val rootView = window.decorView.findViewById<View>(R.id.content)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(0xffffff))
    }



}
