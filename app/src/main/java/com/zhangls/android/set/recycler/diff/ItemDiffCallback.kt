package com.zhangls.android.set.recycler.diff

import android.os.Bundle
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

  override fun getChangePayload(oldItem: ItemData, newItem: ItemData): Any? {
    val bundle = Bundle()
    if (oldItem.title != newItem.title) {
      bundle.putString("title", newItem.title)
    }
    return if (bundle.isEmpty) null else bundle
  }
}