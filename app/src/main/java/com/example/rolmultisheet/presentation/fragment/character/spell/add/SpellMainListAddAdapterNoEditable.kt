package com.example.rolmultisheet.presentation.fragment.character.spell.add

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rolmultisheet.R
import com.example.rolmultisheet.databinding.SpellMainItemFragmentBinding
import com.example.rolmultisheet.domain.model.Spell
import com.example.rolmultisheet.presentation.util.recycler.ItemKeyPositionProvider


object SpellDiffUtil : DiffUtil.ItemCallback<Spell>() {
    override fun areItemsTheSame(oldItem: Spell, newItem: Spell): Boolean =
        oldItem.spellId == newItem.spellId

    override fun areContentsTheSame(oldItem: Spell, newItem: Spell): Boolean = oldItem == newItem
}

class SpellMainListAddAdapterNoEditable :
    ListAdapter<Spell, SpellMainListAddAdapterNoEditable.ViewHolder>(SpellDiffUtil),
    ItemKeyPositionProvider<Long> {

    var selectionTracker: SelectionTracker<Long>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            SpellMainItemFragmentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemKey(position: Int): Long = currentList[position].spellId

    override fun getItemPosition(key: Long): Int = currentList.indexOfFirst { it.spellId == key }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
            currentList[position],
            selectionTracker?.isSelected(getItemKey(position)) ?: false
        )
    }

    inner class ViewHolder(private val binding: SpellMainItemFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                if (binding.spellMainItemDescription.visibility == View.GONE) {
                    binding.spellMainItemDescription.visibility = View.VISIBLE
                    binding.spellMainItemAction.setImageResource(R.drawable.ic_arrow_down_black_24dp)
                } else {
                    binding.spellMainItemDescription.visibility = View.GONE
                    binding.spellMainItemAction.setImageResource(R.drawable.ic_arrow_up_black_24dp)
                }
            }
        }

        fun bind(item: Spell, isSelected: Boolean) {
            binding.run {
                spellMainItemName.text = item.spellName
                if (isSelected) spellMainItemName.append("*")
                spellMainItemDescription.text = item.spellDescription
                root.isActivated = isSelected
            }
        }

        private fun addRippleEffectToView() {
            val outValue = TypedValue()
            binding.root.context.theme
                .resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
            binding.root.setBackgroundResource(outValue.resourceId)
        }
    }


}