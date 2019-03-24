package com.zhangls.android.set.recycler.drag

import androidx.recyclerview.widget.DiffUtil
import com.zhangls.android.set.recycler.ItemData

/**
 * 拖拽功能 DiffUtil
 *
 * @author zhangls
 */
class ItemDiffCallback : DiffUtil.ItemCallback<ItemData>() {
  override fun areItemsTheSame(oldItem: ItemData, newItem: ItemData): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: ItemData, newItem: ItemData): Boolean {
    return oldItem.title == newItem.title
  }
}