package com.example.rolmultisheet.presentation.fragment.character.weapon.add

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rolmultisheet.R
import com.example.rolmultisheet.databinding.WeaponMainItemFragmentBinding
import com.example.rolmultisheet.domain.model.Weapon
import com.example.rolmultisheet.presentation.util.recycler.ItemKeyPositionProvider

object WeaponDiffUtil : DiffUtil.ItemCallback<Weapon>() {
    override fun areItemsTheSame(oldItem: Weapon, newItem: Weapon): Boolean =
        oldItem.weaponId == newItem.weaponId

    override fun areContentsTheSame(oldItem: Weapon, newItem: Weapon): Boolean = oldItem == newItem
}

class WeaponMainListAddAdapterNoEditable :
    ListAdapter<Weapon, WeaponMainListAddAdapterNoEditable.ViewHolder>(WeaponDiffUtil),
    ItemKeyPositionProvider<Long> {

    var selectionTracker: SelectionTracker<Long>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            WeaponMainItemFragmentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemKey(position: Int): Long = currentList[position].weaponId

    override fun getItemPosition(key: Long): Int = currentList.indexOfFirst { it.weaponId == key }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
            currentList[position],
            selectionTracker?.isSelected(getItemKey(position)) ?: false
        )
    }

    inner class ViewHolder(private val binding: WeaponMainItemFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.run {
                root.setOnClickListener {
                    if (weaponMainItemDescription.visibility == View.GONE) {
                        weaponMainItemDescription.visibility = View.VISIBLE
                        weaponMainItemDamageType.visibility = View.VISIBLE
                        weaponMainItemScope.visibility = View.VISIBLE
                        weaponMainItemTwoHand.visibility = View.VISIBLE
                        weaponMainItemWeigth.visibility = View.VISIBLE
                        weaponMainItemAction.setImageResource(R.drawable.ic_arrow_down_black_24dp)
                    } else {
                        weaponMainItemDescription.visibility = View.GONE
                        weaponMainItemDamageType.visibility = View.GONE
                        weaponMainItemScope.visibility = View.GONE
                        weaponMainItemTwoHand.visibility = View.GONE
                        weaponMainItemWeigth.visibility = View.GONE
                        weaponMainItemAction.setImageResource(R.drawable.ic_arrow_up_black_24dp)
                    }
                }
            }
        }

        fun bind(item: Weapon, isSelected: Boolean) {
            binding.run {
                root.isActivated = isSelected
                weaponMainItemName.text = item.weaponName
                if (isSelected) weaponMainItemName.append("*")
                labelWeaponMainItemDamage.text = item.weaponDamage
                weaponMainItemDescription.text =
                    getString(item.weaponDescription, R.string.weapon_main_description)
                labelWeaponMainItemPrice.text =
                    getString(item.weaponPrice.toString(), R.string.item_main_item_price)
                weaponMainItemWeigth.text =
                    getString(item.weaponWeight.toString(), R.string.weapon_main_weight)
                weaponMainItemTwoHand.text =
                    getString(
                        getStringByBoolean(item.weaponIsTwoHand),
                        R.string.weapon_main_two_hand
                    )
                weaponMainItemScope.text =
                    getString(item.weaponScope.toString(), R.string.weapon_main_item_scope)
                weaponMainItemDamageType.text =
                    getString(item.weaponDameType, R.string.weapon_main_damage_type)
            }
        }

        private fun getString(value: String?, @StringRes resource: Int): String =
            binding.root.context.applicationContext.getString(resource, value)

        private fun getStringByBoolean(value: Boolean): String =
            if (value) getStringFromContext(R.string.weapon_main_yes)
            else getStringFromContext(R.string.weapon_main_no)

        private fun getStringFromContext(@StringRes stringResource: Int) =
            binding.root.context.applicationContext.getString(stringResource)
    }
}
