package com.example.rolmultisheet.presentation.fragment.weapon.main

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rolmultisheet.R
import com.example.rolmultisheet.databinding.WeaponMainItemFragmentBinding
import com.example.rolmultisheet.domain.model.Weapon
import com.example.rolmultisheet.presentation.util.recycler.OnItemClickListener


object WeaponDiffUtil : DiffUtil.ItemCallback<Weapon>() {
    override fun areItemsTheSame(oldItem: Weapon, newItem: Weapon): Boolean =
        oldItem.weaponId == newItem.weaponId

    override fun areContentsTheSame(oldItem: Weapon, newItem: Weapon): Boolean = oldItem == newItem
}

class WeaponMainListAdapter :
    ListAdapter<Weapon, WeaponMainListAdapter.ViewHolder>(WeaponDiffUtil) {

    private var onItemClickListener: OnItemClickListener? = null
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            WeaponMainItemFragmentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ViewHolder(private val binding: WeaponMainItemFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            addRippleEffectToView()

            itemView.setOnClickListener {
                onItemClickListener?.onItemClick(adapterPosition)
            }

            binding.weaponMainItemAction.setOnClickListener {
                if (binding.weaponMainItemDescription.visibility == View.GONE) {
                    binding.weaponMainItemDescription.visibility = View.VISIBLE
                    binding.weaponMainItemAction.setImageResource(R.drawable.ic_arrow_down_black_24dp)
                } else {
                    binding.weaponMainItemDescription.visibility = View.GONE
                    binding.weaponMainItemAction.setImageResource(R.drawable.ic_arrow_up_black_24dp)
                }
            }
        }

        fun bind(item: Weapon) {
            binding.run {
                weaponMainItemName.text = item.weaponName
                weaponMainItemDescription.text = item.weaponDescription
                labelWeaponMainItemPrice.text =
                    binding.root.context.applicationContext.getString(
                        R.string.item_main_item_price,
                        item.weaponPrice
                    )
                labelWeaponMainItemDamage.text = item.weaponDamage
            }
        }

        private fun addRippleEffectToView() {
            val outValue = TypedValue()
            binding.root.context.theme
                .resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
            binding.weaponMainItemSubroot.setBackgroundResource(outValue.resourceId)

        }
    }
}