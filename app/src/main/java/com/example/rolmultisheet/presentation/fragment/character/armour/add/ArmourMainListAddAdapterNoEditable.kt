package com.example.rolmultisheet.presentation.fragment.character.armour.add

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rolmultisheet.R
import com.example.rolmultisheet.databinding.ArmourMainItemFragmentBinding
import com.example.rolmultisheet.domain.model.Armour
import com.example.rolmultisheet.presentation.util.recycler.ItemKeyPositionProvider


object ArmourDiffUtil : DiffUtil.ItemCallback<Armour>() {
    override fun areItemsTheSame(oldItem: Armour, newItem: Armour): Boolean =
        oldItem.armourId == newItem.armourId

    override fun areContentsTheSame(oldItem: Armour, newItem: Armour): Boolean = oldItem == newItem
}

class ArmourMainListAddAdapterNoEditable :
    ListAdapter<Armour, ArmourMainListAddAdapterNoEditable.ViewHolder>(ArmourDiffUtil),
    ItemKeyPositionProvider<Long> {

    var selectionTracker: SelectionTracker<Long>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ArmourMainItemFragmentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemKey(position: Int): Long = currentList[position].armourId

    override fun getItemPosition(key: Long): Int = currentList.indexOfFirst { it.armourId == key }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
            currentList[position],
            selectionTracker?.isSelected(getItemKey(position)) ?: false
        )
    }

    inner class ViewHolder(private val binding: ArmourMainItemFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                if (binding.armourMainItemDescription.visibility == View.GONE) {
                    binding.armourMainItemDescription.visibility = View.VISIBLE
                    binding.labelArmourMainItemClass.visibility = View.VISIBLE
                    binding.imageArmourMainItemClass.visibility = View.VISIBLE
                    binding.armourMainItemAction.setImageResource(R.drawable.ic_arrow_down_black_24dp)
                } else {
                    binding.armourMainItemDescription.visibility = View.GONE
                    binding.labelArmourMainItemClass.visibility = View.GONE
                    binding.imageArmourMainItemClass.visibility = View.GONE
                    binding.armourMainItemAction.setImageResource(R.drawable.ic_arrow_up_black_24dp)
                }
            }
        }

        fun bind(item: Armour, isSelected: Boolean) {
            binding.run {
                armourMainItemName.text = item.armourName
                armourMainItemDescription.text = item.armourDescription
                labelArmourMainItemPrice.text =
                    binding.root.context.applicationContext.getString(
                        R.string.item_main_item_price,
                        item.armourPrice
                    )
                labelArmourMainItemClass.text = item.armourClass.toString()
                root.isActivated = isSelected
            }
        }
    }
}