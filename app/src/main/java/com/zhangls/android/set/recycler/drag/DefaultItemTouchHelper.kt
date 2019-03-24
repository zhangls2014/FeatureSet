package com.zhangls.android.set.recycler.drag

import androidx.recyclerview.widget.ItemTouchHelper

/**
 * 自定义 ItemTouchHelper
 *
 * @author zhangls
 */
class DefaultItemTouchHelper(private var itemTouchHelpCallback: DefaultItemTouchHelpCallback) :
  ItemTouchHelper(itemTouchHelpCallback) {
  /**
   * 设置是否可以被拖拽
   *
   * @param canDrag 是true，否false
   */
  fun setDragEnable(canDrag: Boolean) {
    itemTouchHelpCallback.isCanDrag = canDrag
  }

  /**
   * 设置是否可以被滑动
   *
   * @param canSwipe 是true，否false
   */
  fun setSwipeEnable(canSwipe: Boolean) {
    itemTouchHelpCallback.isCanSwipe = canSwipe
  }
}