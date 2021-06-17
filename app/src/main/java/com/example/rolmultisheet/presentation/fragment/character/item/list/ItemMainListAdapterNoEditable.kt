package com.example.rolmultisheet.presentation.fragment.character.item.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rolmultisheet.R
import com.example.rolmultisheet.databinding.ItemMainItemFragmentBinding
import com.example.rolmultisheet.domain.model.Item

object ItemDiffUtil : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
        oldItem.itemId == newItem.itemId

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem == newItem
}

class ItemMainListAdapterNoEditable :
    ListAdapter<Item, ItemMainListAdapterNoEditable.ViewHolder>(ItemDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemMainItemFragmentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ViewHolder(private val binding: ItemMainItemFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                if (binding.itemMainItemDescription.visibility == View.GONE) {
                    binding.itemMainItemDescription.visibility = View.VISIBLE
                    binding.itemMainItemWeight.visibility = View.VISIBLE
                    binding.itemMainItemAction.setImageResource(R.drawable.ic_arrow_down_black_24dp)
                } else {
                    binding.itemMainItemDescription.visibility = View.GONE
                    binding.itemMainItemWeight.visibility = View.GONE
                    binding.itemMainItemAction.setImageResource(R.drawable.ic_arrow_up_black_24dp)
                }
            }
        }

        fun bind(item: Item) {
            binding.run {
                itemMainItemName.text = item.itemName
                itemMainItemDescription.text =
                    getString(item.itemDescription, R.string.item_main_item_description)
                labelItemMainItemPrice.text =
                    getString(item.itemPrice.toString(), R.string.item_main_item_price)
                itemMainItemWeight.text =
                    getString(item.itemWeight.toString(), R.string.item_main_item_weight)
            }
        }

        private fun getString(value: String?, @StringRes resource: Int): String =
            binding.root.context.applicationContext.getString(resource, value)
    }
}