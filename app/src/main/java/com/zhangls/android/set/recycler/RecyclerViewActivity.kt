package com.zhangls.android.set.recycler

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhangls.android.set.R
import com.zhangls.android.set.recycler.diff.DiffRecyclerAdapter
import com.zhangls.android.set.recycler.drag.DefaultItemTouchHelpCallback
import com.zhangls.android.set.recycler.drag.DefaultItemTouchHelper
import com.zhangls.android.set.recycler.drag.DragRecyclerAdapter
import kotlinx.android.synthetic.main.activity_recycler_view.*
import java.util.*


/**
 * 列表
 *
 * @author zhangls
 */
class RecyclerViewActivity : AppCompatActivity() {

  private val list = mutableListOf<ItemData>()


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
    supportActionBar?.setDisplayHomeAsUpEnabled(true)

    val type = intent.getSerializableExtra(ARG_FUNCTION_TYPE)
    if (type is ItemType) {
      when (type) {
        ItemType.TYPE_DRAG -> loadDragInfo()
        ItemType.TYPE_DIFF -> loadDiffInfo()
      }
    }
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      android.R.id.home -> {
        onBackPressed()
      }
      else -> {
      }
    }
    return super.onOptionsItemSelected(item)
  }

  /**
   * 加载拖拽功能
   */
  private fun loadDragInfo() {
    val adapter = DragRecyclerAdapter()
    recycler.layoutManager = LinearLayoutManager(this)
    recycler.adapter = adapter
    recycler.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))

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
    ).apply {
      attachToRecyclerView(recycler)
      setDragEnable(true)
      setSwipeEnable(false)
    }

    for (index in 1..10) {
      list.add(ItemData(index.toLong(), "item -> $index", ItemType.TYPE_DRAG))
    }
    adapter.submitList(list)
  }

  /**
   * 加载Diff功能
   */
  private fun loadDiffInfo() {
    val adapter = DiffRecyclerAdapter()
    recycler.layoutManager = LinearLayoutManager(this)
    recycler.adapter = adapter
    recycler.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))

    for (index in 1..10) {
      list.add(ItemData(index.toLong(), "item -> $index", ItemType.TYPE_DIFF))
    }
    adapter.submitList(list)
  }
}
