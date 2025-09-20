package com.app.pages.menu.overtime

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.view.isGone
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.common.base.BaseActivity
import com.app.common.LoadingIndicator
import com.app.common.manager.alert.AlertManager
import com.app.databinding.OvertimeActivityBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlin.jvm.java

class OverTimeActivity: BaseActivity() {
    private lateinit var binding: OvertimeActivityBinding
    private lateinit var adapter: OverTimeAdapter

    private val viewModel : OverTimeViewModel by lazy { ViewModelProvider(this)[OverTimeViewModel::class.java] }

    companion object {
        fun launch(activity: Activity) {
            val intent = Intent(activity, OverTimeActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = OvertimeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
    }

    override fun initUI() {
        super.initUI()
        setRecycleView()
    }

    override fun initBinding() {
        super.initBinding()
        binding.btnRequest.setOnClickListener {
            gotoRequest()
        }

        viewModel.completionError = { error ->
            LoadingIndicator.hide()
            error?.message?.let {
                AlertManager.createAlert(this, it)
            }
        }
        viewModel.completionReloadData = {
            binding.emptyView.emptyView.isGone = !viewModel.overTime.data.isEmpty
            adapter.setData(viewModel.overTime.data)
        }
    }

    override fun setData() {
        super.setData()
        title = "Overtime"
    }

    override fun loadData() {
        super.loadData()
        getOvertime()
    }

    private fun setRecycleView() {
        adapter = OverTimeAdapter {}
        binding.recycleView.adapter = adapter
        binding.recycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val canScroll = recyclerView.canScrollVertically(1)
                if (!canScroll && viewModel.canLoadMore()) {
                    getOvertime()
                }
            }
        })

        binding.swipeRefreshLayout.setOnRefreshListener {
            loadData()
            viewModel.flushData()
            runOnUiThread {
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
    }
    //API
    private fun getOvertime() {
        LoadingIndicator.createDialog(this)
        viewModel.getTimeOffList()
    }
    //ACTION
    private fun gotoRequest() {
//        OverTimeRequestActivity.launch(this@OverTimeActivity) {
//            GlobalScope.async(Dispatchers.Main) {
//                getOvertime()
//            }
//        }
    }
}
