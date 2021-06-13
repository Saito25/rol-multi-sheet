package com.example.rolmultisheet.presentation.fragment.character.armour.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rolmultisheet.R
import com.example.rolmultisheet.databinding.ArmourMainItemFragmentBinding
import com.example.rolmultisheet.domain.model.Armour

object ArmourDiffUtil : DiffUtil.ItemCallback<Armour>() {
    override fun areItemsTheSame(oldItem: Armour, newItem: Armour): Boolean =
        oldItem.armourId == newItem.armourId

    override fun areContentsTheSame(oldItem: Armour, newItem: Armour): Boolean = oldItem == newItem
}

class ArmourMainListAdapterNoEditable :
    ListAdapter<Armour, ArmourMainListAdapterNoEditable.ViewHolder>(ArmourDiffUtil) {

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
            binding.run {
                root.setOnClickListener {
                    if (armourMainItemDescription.visibility == View.GONE) {
                        armourMainItemDescription.visibility = View.VISIBLE
                        imageArmourMainItemClass.visibility = View.VISIBLE
                        labelArmourMainItemClass.visibility = View.VISIBLE
                        armourMainItemMaxBonus.visibility = View.VISIBLE
                        armourMainItemRequiredStrength.visibility = View.VISIBLE
                        armourMainItemStealth.visibility = View.VISIBLE
                        armourMainItemWeight.visibility = View.VISIBLE
                        armourMainItemAction.setImageResource(R.drawable.ic_arrow_down_black_24dp)
                    } else {
                        armourMainItemDescription.visibility = View.GONE
                        labelArmourMainItemClass.visibility = View.GONE
                        imageArmourMainItemClass.visibility = View.GONE
                        armourMainItemMaxBonus.visibility = View.GONE
                        armourMainItemRequiredStrength.visibility = View.GONE
                        armourMainItemStealth.visibility = View.GONE
                        armourMainItemWeight.visibility = View.GONE
                        armourMainItemAction.setImageResource(R.drawable.ic_arrow_up_black_24dp)
                    }
                }
            }
        }

        fun bind(item: Armour) {
            binding.run {
                armourMainItemName.text = item.armourName
                armourMainItemDescription.text =
                    getString(item.armourDescription, R.string.armour_main_item_description)
                labelArmourMainItemPrice.text =
                    getString(item.armourPrice.toString(), R.string.item_main_item_price)
                labelArmourMainItemClass.text = item.armourClass.toString()
                armourMainItemStealth.text = getString(
                    getStringByBoolean(item.armourStealthDisadvantage),
                    R.string.armour_main_item_stealth
                )
                armourMainItemWeight.text =
                    getString(item.armourWeight.toString(), R.string.armour_main_item_weight)
                armourMainItemRequiredStrength.text = getString(
                    item.armourRequiredMinStrength.toString(),
                    R.string.armour_main_item_required_Strength
                )
                armourMainItemMaxBonus.text =
                    getString(item.armourMaxBonus.toString(), R.string.armour_main_item_max_bonus)
            }
        }

        private fun getString(value: String?, @StringRes resource: Int): String =
            binding.root.context.applicationContext.getString(resource, value)

        private fun getStringByBoolean(value: Boolean): String =
            if (value) getStringFromContext(R.string.armour_main_item_stealth_yes)
            else getStringFromContext(R.string.armour_main_item_stealth_no)

        private fun getStringFromContext(@StringRes stringResource: Int) =
            binding.root.context.applicationContext.getString(stringResource)
    }
}
