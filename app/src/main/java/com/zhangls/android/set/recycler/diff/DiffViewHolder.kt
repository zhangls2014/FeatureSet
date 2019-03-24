package com.zhangls.android.set.recycler.diff

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.zhangls.android.set.R

/**
 * Diff 列表 Item ViewHolder
 *
 * @author zhangls
 */
class DiffViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  val text: AppCompatTextView = itemView.findViewById(R.id.text)
}