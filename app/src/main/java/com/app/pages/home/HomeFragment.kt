package com.app.pages.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.common.base.BaseFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class HomeFragment: BaseFragment() {
//  private lateinit var binding: HomeFragmentBinding
//  private lateinit var adapter: HomeAdapter
//
//  private val viewModel : HomeViewModel by lazy { ViewModelProvider(this)[HomeViewModel::class.java] }
//
//  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//    binding = HomeFragmentBinding.inflate(inflater, container, false)
//    return binding.root
//  }
//
//  override fun onPause() {
//    super.onPause()
//    hideNavigation(false)
//  }
//
//  override fun onResume() {
//    super.onResume()
//    hideNavigation(true)
//  }
//
//  override fun initUI() {
//    super.initUI()
//    setRecycleView()
////        hideNavigation(true)
//  }
//
//  override fun initBinding() {
//    super.initBinding()
//    viewModel.completionError = { error ->
//      error?.message?.let {
//        AlertManager.createAlert(requireActivity(), it)
//      }
//    }
//  }
//
//  override fun loadData() {
//    super.loadData()
//    getOperationalTime()
//    getNotification()
//  }
//
////    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
////        super.onViewCreated(view, savedInstanceState)
////        hideNavigation(true)
////    }
//
//  private fun setRecycleView() {
//    adapter = HomeAdapter {}
//
//    adapter.completionSelectedClockInOut = { isClockIn ->
//      GlobalScope.async(Dispatchers.Main) {
//        activity?.let { LiveAttendanceActivity.launch(it, if(isClockIn) LiveAttendanceType.clockin else LiveAttendanceType.clockout) }
//      }
//    }
//
//    adapter.completionSelectedMenu = { index ->
//      when(index) {
//        0 ->
//          GlobalScope.async(Dispatchers.Main) {
//            activity?.let { HistoryAttendanceActivity.launch(it) }
//          }
//        1 ->
//          GlobalScope.async(Dispatchers.Main) {
//            activity?.let { TimeOffActivity.launch(it) }
//          }
//        2 ->
//          GlobalScope.async(Dispatchers.Main) {
//            activity?.let { OverTimeActivity.launch(it) }
//          }
//        3 ->
//          GlobalScope.async(Dispatchers.Main) {
//            activity?.let { TrackingActivity.launch(it) }
//          }
//      }
//
//    }
//
//    adapter.completionSeeMoreNotification = {
//      GlobalScope.async(Dispatchers.Main) {
//        activity?.let { NotificationActivity.launch(it) }
//      }
//    }
//    binding.recycleView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
//    binding.recycleView.adapter = adapter
//
//    binding.swipeRefreshLayout.setOnRefreshListener {
//      loadData()
//      GlobalScope.async(Dispatchers.Main) {
//        binding.swipeRefreshLayout.isRefreshing = false
//      }
//    }
//
//  }
//
//  //API
//  private fun getOperationalTime() {
////        LoadingIndicator.createDialog(requireActivity())
//    viewModel.getOperationalTime {
//      LoadingIndicator.hide()
//      adapter.setOperationalTime()
//    }
//  }
//
//  private fun getNotification() {
//    LoadingIndicator.createDialog(requireActivity())
//    viewModel.getNotification {
//      LoadingIndicator.hide()
//      adapter.setNotification(viewModel.notification.data)
//    }
//  }

}
