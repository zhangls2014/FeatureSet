package com.zhangls.android.set

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.zhangls.android.set.recycler.RecyclerViewFragment
import com.zhangls.android.set.verification.VerificationFragment
import kotlinx.android.synthetic.main.activity_main.*


/**
 * 首页
 *
 * @author zhangls
 */
class MainActivity : AppCompatActivity() {

  /**
   * Tab 适配器
   */
  private val mSectionsPagerAdapter: SectionsPagerAdapter by lazy {
    SectionsPagerAdapter(
      supportFragmentManager,
      tabItems
    )
  }
  /**
   * Tab item set
   */
  private val tabItems = mutableListOf<Fragment>()


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)

    tabs.apply {
      addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))

      tabMode = TabLayout.MODE_SCROLLABLE
    }

    container.apply {
      offscreenPageLimit = tabItems.size
      adapter = mSectionsPagerAdapter
      addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
    }

    // 内容填充
    tabItems.add(RecyclerViewFragment.newInstance())
    tabs.addTab(tabs.newTab().setText("Recycler"))

    tabItems.add(VerificationFragment.newInstance())
    tabs.addTab(tabs.newTab().setText("验证码"))

    mSectionsPagerAdapter.notifyDataSetChanged()
  }


  /**
   * A [FragmentPagerAdapter] that returns a fragment corresponding to
   * one of the sections/tabs/pages.
   */
  class SectionsPagerAdapter(fm: FragmentManager, private val items: MutableList<Fragment>) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = items[position]

    override fun getCount(): Int = items.size
  }
}
