package com.app.common.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

open class BaseFragment: Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initBinding()
        setData()
        loadData()
    }

    override fun onStop() {
        super.onStop()
    }

    open fun initBinding() {}

    open fun initUI() {}

    open fun setData() {}

    open fun loadData() {}

    open fun hideNavigation(isHide: Boolean) {
//        if(isHide) (activity as? AppCompatActivity)?.supportActionBar?.show() else (activity as? AppCompatActivity)?.supportActionBar?.hide()
    }


}
