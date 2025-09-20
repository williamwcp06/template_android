package com.app.pages

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.app.R
import com.app.common.base.BaseActivity
import com.app.databinding.TabbarActivityBinding
import com.app.pages.home.HomeFragment

class TabbarActivity : BaseActivity() {

  private lateinit var binding: TabbarActivityBinding

//  private val inboxFragment: InboxFragment = InboxFragment()
  //    private val reportFragment: AttReportFragment = AttReportFragment()
//  private val profileFragment: ProfileFragment = ProfileFragment()
  private val homeFragment: HomeFragment = HomeFragment()

  private lateinit var activeFragment: Fragment

  companion object {
    fun launch(activity: Activity, userName: String) {
      val intent = Intent(activity, TabbarActivity::class.java)
      activity.startActivity(intent)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = TabbarActivityBinding.inflate(layoutInflater)
    setContentView(binding.root)
//        loadFragment(attandaceFragment)
    initFragment()
    setNavbar()
  }

  private fun loadFragment(fragment: Fragment) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(R.id.container, fragment)
    transaction.commit()
  }

  private fun initFragment() {
//    supportFragmentManager.beginTransaction().add(R.id.container, inboxFragment, inboxFragment.tag).hide(inboxFragment).commit()
//        supportFragmentManager.beginTransaction().add(R.id.container, reportFragment, reportFragment.tag).hide(reportFragment).commit()
//    supportFragmentManager.beginTransaction().add(R.id.container, profileFragment, profileFragment.tag).hide(profileFragment).commit()
    supportFragmentManager.beginTransaction().add(R.id.container, homeFragment, homeFragment.tag).show(homeFragment).commit()
    activeFragment = homeFragment
    supportActionBar?.hide()
    this.setTitleNavigation("Home")
  }

  private fun hideShowFragment(fragment: Fragment, title: String, useNavigation: Boolean) {
    supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment).commit()
    if(useNavigation) supportActionBar?.show() else supportActionBar?.hide()

    activeFragment.onStop()
    this.setTitleNavigation(title)
    activeFragment = fragment
  }

  private fun setNavbar() {
    binding.bottomNav.itemIconTintList = null
    binding.bottomNav.setOnNavigationItemReselectedListener {  }
    binding.bottomNav.setOnItemSelectedListener { item ->
      when (item.itemId) {
        //attandence
        R.id.home -> {
          hideShowFragment(homeFragment, "Home", false)
        }
        //student
//        R.id.inbox -> {
//          hideShowFragment(inboxFragment, "Inbox", false)
//        }
        //report
//                R.id.report -> {
//                    hideShowFragment(reportFragment, "Report")
//                }
//                //profile
//        R.id.profile -> {
//          hideShowFragment(profileFragment, "Profile", false)
//        }
      }
      true
    }
  }

  fun setTitleNavigation(title: String) {
    this.title = title

  }
}
