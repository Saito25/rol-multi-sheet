package com.example.rolmultisheet.presentation.fragment.armour.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rolmultisheet.R
import com.example.rolmultisheet.databinding.ArmourMainItemFragmentBinding
import com.example.rolmultisheet.domain.model.Armour
import com.example.rolmultisheet.presentation.util.recycler.OnItemClickListener

object ArmourDiffUtil : DiffUtil.ItemCallback<Armour>() {
    override fun areItemsTheSame(oldItem: Armour, newItem: Armour): Boolean =
        oldItem.armourId == newItem.armourId

    override fun areContentsTheSame(oldItem: Armour, newItem: Armour): Boolean = oldItem == newItem
}

class ArmourMainListAdapter :
    ListAdapter<Armour, ArmourMainListAdapter.ViewHolder>(ArmourDiffUtil) {

    private var onItemClickListener: OnItemClickListener? = null
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ArmourMainItemFragmentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ViewHolder(private val binding: ArmourMainItemFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onItemClickListener?.onItemClick(adapterPosition)
            }

            binding.armourMainItemAction.setOnClickListener {
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

        fun bind(item: Armour) {
            binding.run {
                armourMainItemName.text = item.armourName
                armourMainItemDescription.text = item.armourDescription
                labelArmourMainItemPrice.text =
                    binding.root.context.applicationContext.getString(
                        R.string.item_main_item_price,
                        item.armourPrice
                    )
                labelArmourMainItemClass.text = item.armourClass.toString()
            }
        }
    }
}