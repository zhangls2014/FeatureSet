package com.zhangls.android.set.recycler.drag

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import com.zhangls.android.set.R
import com.zhangls.android.set.recycler.ItemData


/**
 * 拖拽列表适配器
 *
 * @author zhangls
 */
class DragRecyclerAdapter : ListAdapter<ItemData, DragViewHolder>(ItemDiffCallback()) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DragViewHolder {
    val inflate =
      LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_list_text, parent, false)
    return DragViewHolder(inflate)
  }

  override fun onBindViewHolder(holder: DragViewHolder, position: Int) {
    holder.itemView.setBackgroundColor(
      ContextCompat.getColor(
        holder.itemView.context,
        android.R.color.white
      )
    )
    holder.text.text = getItem(holder.adapterPosition).title
  }

}