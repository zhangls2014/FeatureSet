package com.zhangls.android.set.recycler.drag

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * @author zhangls
 */
class DefaultItemTouchHelpCallback(
  private var onItemTouchCallbackListener: OnItemTouchCallbackListener? = null
) : ItemTouchHelper.Callback() {
  /**
   * 是否可以拖拽
   */
  var isCanDrag = false
  /**
   * 是否可以被滑动
   */
  var isCanSwipe = false


  interface OnItemTouchCallbackListener {
    /**
     * 当某个Item被滑动删除的时候
     *
     * @param adapterPosition item 的 position
     */
    fun onSwiped(adapterPosition: Int)

    /**
     * 当两个 Item 位置互换的时候被回调
     *
     * @param srcPosition    拖拽的 item 的position
     * @param targetPosition 目的地的 Item 的 position
     * @return 开发者处理了操作应该返回 true，开发者没有处理就返回 false
     */
    fun onMove(srcPosition: Int, targetPosition: Int): Boolean

    fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int)
  }

  /**
   * 当用户拖拽或者滑动 Item 的时候需要我们告诉系统滑动或者拖拽的方向
   *
   * @param recyclerView
   * @param viewHolder
   * @return
   */
  override fun getMovementFlags(
    recyclerView: RecyclerView,
    viewHolder: RecyclerView.ViewHolder
  ): Int {
    val layoutManager = recyclerView.layoutManager
    if (layoutManager is GridLayoutManager) {
      // flag 如果值是 0，相当于这个功能被关闭
      val dragFlag =
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or ItemTouchHelper.UP or ItemTouchHelper.DOWN
      val swipeFlag = 0
      return makeMovementFlags(if (isCanDrag) dragFlag else 0, swipeFlag)
    } else if (layoutManager is LinearLayoutManager) {
      var dragFlag = 0
      var swipeFlag = 0
      when (layoutManager.orientation) {
        LinearLayoutManager.HORIZONTAL -> {
          swipeFlag = ItemTouchHelper.UP or ItemTouchHelper.DOWN
          dragFlag = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        }
        LinearLayoutManager.VERTICAL -> {
          dragFlag = ItemTouchHelper.UP or ItemTouchHelper.DOWN
          swipeFlag = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        }
      }
      return makeMovementFlags(if (isCanDrag) dragFlag else 0, if (isCanSwipe) swipeFlag else 0)
    }
    return 0
  }

  override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
    super.onSelectedChanged(viewHolder, actionState)
    if (onItemTouchCallbackListener != null) {
      onItemTouchCallbackListener!!.onSelectedChanged(viewHolder, actionState)
    }
  }

  /**
   * 当Item被拖拽的时候被回调
   *
   * @param recyclerView     recyclerView
   * @param srcViewHolder    拖拽的ViewHolder
   * @param targetViewHolder 目的地的viewHolder
   * @return
   */
  override fun onMove(
    recyclerView: RecyclerView, srcViewHolder: RecyclerView.ViewHolder,
    targetViewHolder: RecyclerView.ViewHolder
  ): Boolean {
    return if (onItemTouchCallbackListener != null) {
      onItemTouchCallbackListener!!.onMove(
        srcViewHolder.adapterPosition,
        targetViewHolder.adapterPosition
      )
    } else {
      false
    }
  }

  override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
    if (onItemTouchCallbackListener != null) {
      onItemTouchCallbackListener!!.onSwiped(viewHolder.adapterPosition)
    }
  }
}