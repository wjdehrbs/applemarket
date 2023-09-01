package com.example.homwark


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homwark.databinding.ItemRecyclerviewBinding
import java.text.DecimalFormat

class MyAdapter(private val mItems: MutableList<MyItem>) : RecyclerView.Adapter<MyAdapter.Holder>() {

    interface ItemClick {
        fun onClick(view : View, position : Int)
    }

    interface ItemLongClick{
        fun onLongClick(view: View, position: Int)
    }

    var itemClick : ItemClick? = null
    var itemLongClick : ItemLongClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }

        holder.itemView.setOnLongClickListener() OnLongClickListener@{
            itemLongClick?.onLongClick(it, position)
            return@OnLongClickListener true
        }



        val item = mItems[position]
        holder.iconView.setImageResource(item.Image)
        holder.tvItemTitle.text = item.ItemTitle
        holder.tvAddress.text = item.Address
        val price = item.Price
        holder.tvPrice.text = DecimalFormat("#,###").format(price) + "원"
        holder.tvItemChat.text = item.ChatCnt.toString()
        holder.tvItemLike.text = item.InterestCnt.toString()
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    inner class Holder(val binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        val iconView = binding.iconItem
        val tvItemTitle = binding.itemName
        val tvAddress = binding.itemLive
        val tvPrice = binding.itemprice
        val tvItemChat = binding.tvChating
        val tvItemLike = binding.tvHeart
    }
}