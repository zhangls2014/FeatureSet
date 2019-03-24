package com.zhangls.android.set.recycler.diff

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zhangls.android.set.R
import com.zhangls.android.set.recycler.ItemData


/**
 * Diff 列表适配器，在这里不使用{@linkplain ListAdapter}
 *
 * @see ListAdapter
 *
 * @author zhangls
 */
class DiffRecyclerAdapter : RecyclerView.Adapter<DiffViewHolder>() {

  /**
   * Differ
   */
  private val mHelper = AsyncListDiffer<ItemData>(this, ItemDiffCallback())

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiffViewHolder {
    val inflate =
      LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_list_text, parent, false)
    return DiffViewHolder(inflate)
  }

  override fun getItemCount(): Int = mHelper.currentList.size

  override fun onBindViewHolder(holder: DiffViewHolder, position: Int) {
    holder.itemView.setBackgroundColor(
      ContextCompat.getColor(
        holder.itemView.context,
        android.R.color.white
      )
    )
    holder.text.text = getItem(holder.adapterPosition).title
    holder.text.setOnClickListener {
      val items = mutableListOf<ItemData>()
      mHelper.currentList.forEach { items.add(it) }

      val value = items[holder.adapterPosition].title + "."
      items[holder.adapterPosition] = items[holder.adapterPosition].copy(title = value)

      submitList(items)
    }
  }

  override fun onBindViewHolder(holder: DiffViewHolder, position: Int, payloads: MutableList<Any>) {
    if (payloads.isNullOrEmpty()) {
      onBindViewHolder(holder, position)
    } else {
      val bundle: Bundle = payloads[0] as Bundle
      holder.text.text = bundle.getString("title")
    }
  }

  private fun getItem(position: Int): ItemData {
    return mHelper.currentList[position]
  }

  fun submitList(list: List<ItemData>) {
    mHelper.submitList(list)
  }
}