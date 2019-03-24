package com.zhangls.android.set.recycler


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.zhangls.android.set.R


/**
 * RecyclerView 示例 [Fragment]
 *
 * @author zhangls
 */
class RecyclerViewFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_recycler_view, container, false)
  }

  companion object {
    /**
     * 返回一个 Fragment 实例
     */
    fun newInstance(): RecyclerViewFragment = RecyclerViewFragment()
  }
}
