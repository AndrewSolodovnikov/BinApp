package com.sol.binapp.ui.binHistory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sol.binapp.data.BinWithNumber
import com.sol.binapp.databinding.ItemBinHistoryBinding

class BinHistoryAdapter(private var items: List<BinWithNumber>) :
    RecyclerView.Adapter<BinHistoryAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemBinHistoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemBinHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        with(viewHolder) {
            with(items[position]) {
                binding.tvCountryEmojiHistory.text = this.bin.country.emoji
                binding.tvBinNumberHistory.text = this.number.number
                binding.itemBinHistory.setOnClickListener { itemClickListener?.onItemClick(this) }
            }
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateBinList(newBinList: List<BinWithNumber>) {
        items = newBinList
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(bin: BinWithNumber)
    }

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }
}
