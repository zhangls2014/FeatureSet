package com.zhangls.android.set.recycler.drag

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.zhangls.android.set.R

/**
 * 拖拽列表 Item ViewHolder
 *
 * @author zhangls
 */
class DragViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  val text: AppCompatTextView = itemView.findViewById(R.id.text)
}