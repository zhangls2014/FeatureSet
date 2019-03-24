package com.zhangls.android.set.recycler


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhangls.android.set.R
import kotlinx.android.synthetic.main.fragment_recycler_view.*


/**
 * RecyclerView 示例 [Fragment]
 *
 * @author zhangls
 */
class RecyclerViewFragment : Fragment() {

  private val items = mutableListOf<ItemData>()
  private val itemAdapter = ItemAdapter(items)

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_recycler_view, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    recycler.layoutManager = LinearLayoutManager(context)
    recycler.adapter = itemAdapter

    items.add(ItemData(1, "Item 拖拽功能", ItemType.TYPE_DRAG))
    itemAdapter.notifyDataSetChanged()
  }


  class ItemAdapter(private val items: MutableList<ItemData>) :
    RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
      return ItemViewHolder(
        LayoutInflater.from(parent.context).inflate(
          R.layout.recycler_item_fragment_item,
          parent,
          false
        )
      )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
      holder.title.text = items[holder.adapterPosition].title

      holder.title.setOnClickListener {
        when (items[holder.adapterPosition].type) {
          ItemType.TYPE_DRAG -> {
            RecyclerViewActivity.actionStart(it.context, items[holder.adapterPosition].type)
          }
        }
      }
    }
  }

  class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: AppCompatTextView = itemView.findViewById(R.id.tvTitle)
  }

  companion object {
    /**
     * 返回一个 Fragment 实例
     */
    fun newInstance(): RecyclerViewFragment = RecyclerViewFragment()
  }
}
