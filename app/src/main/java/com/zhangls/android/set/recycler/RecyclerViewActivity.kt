package com.zhangls.android.set.recycler

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.*
import com.zhangls.android.set.R
import com.zhangls.android.set.recycler.drag.DefaultItemTouchHelpCallback
import com.zhangls.android.set.recycler.drag.DefaultItemTouchHelper
import kotlinx.android.synthetic.main.activity_recycler_view.*
import java.util.*


/**
 * 列表
 *
 * @author zhangls
 */
class RecyclerViewActivity : AppCompatActivity() {

  private val list = mutableListOf<ItemData>()
  private val adapter = RecyclerAdapter()
  private val touchHelper by lazy {
    DefaultItemTouchHelper(
      DefaultItemTouchHelpCallback(
        object : DefaultItemTouchHelpCallback.OnItemTouchCallbackListener {

          override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {

          }

          override fun onSwiped(adapterPosition: Int) {}

          override fun onMove(srcPosition: Int, targetPosition: Int): Boolean {
            Collections.swap(list, srcPosition, targetPosition)
            adapter.notifyItemMoved(srcPosition, targetPosition)
            return true
          }
        })
    )
  }


  companion object {
    /**
     * 参数键值，类型
     */
    private const val ARG_FUNCTION_TYPE = "function_type"

    /**
     * Activity 入口方法
     *
     * @param type 功能类型
     */
    fun actionStart(context: Context, type: ItemType) {
      val intent = Intent(context, RecyclerViewActivity::class.java).apply {
        putExtra(ARG_FUNCTION_TYPE, type)
      }
      context.startActivity(intent)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_recycler_view)
    setSupportActionBar(toolbar)

    val type = intent.getSerializableExtra(ARG_FUNCTION_TYPE)
    if (type is ItemType) {
      when (type) {
        ItemType.TYPE_DRAG -> loadDragInfo()
        else -> {
        }
      }
    }

    // 填充数据
    for (index in 1..10) {
      list.add(ItemData(index.toLong(), "item -> $index", ItemType.TYPE_DRAG))
    }
    adapter.submitList(list)
  }

  /**
   * 加载拖拽功能
   */
  private fun loadDragInfo() {
    recycler.layoutManager = LinearLayoutManager(this)
    recycler.adapter = adapter
    recycler.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))

    touchHelper.attachToRecyclerView(recycler)
    touchHelper.setDragEnable(true)
    touchHelper.setSwipeEnable(false)
  }

  inner class RecyclerAdapter : ListAdapter<ItemData, ViewHolder>(MessageDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val inflate =
        LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_list_text, parent, false)
      return ViewHolder(inflate)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      holder.itemView.setBackgroundColor(
        ContextCompat.getColor(
          holder.itemView.context,
          android.R.color.white
        )
      )
      holder.text.text = getItem(holder.adapterPosition).title
      holder.text.setOnClickListener {
        val value = list[holder.adapterPosition].title + "."
        list[holder.adapterPosition] = list[holder.adapterPosition].copy(title = value)

        val items = mutableListOf<ItemData>()
        list.forEach { items.add(it) }

        adapter.submitList(items)
      }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
      if (payloads.isNullOrEmpty()) {
        onBindViewHolder(holder, position)
      } else {
        val bundle: Bundle = payloads[0] as Bundle
        holder.text.text = bundle.getString("title")
      }
    }
  }

  class MessageDiffCallback : DiffUtil.ItemCallback<ItemData>() {
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

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val text: AppCompatTextView = itemView.findViewById(R.id.text)
  }
}
