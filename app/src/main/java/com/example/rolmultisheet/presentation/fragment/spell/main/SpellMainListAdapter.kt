package com.example.rolmultisheet.presentation.fragment.spell.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rolmultisheet.databinding.SpellMainItemFragmentBinding
import com.example.rolmultisheet.domain.model.Spell
import com.example.rolmultisheet.presentation.util.recycler.OnItemClickListener

object SpellDiffUtil : DiffUtil.ItemCallback<Spell>() {
    override fun areItemsTheSame(oldItem: Spell, newItem: Spell): Boolean =
        oldItem.spellId == newItem.spellId

    override fun areContentsTheSame(oldItem: Spell, newItem: Spell): Boolean = oldItem == newItem
}

class SpellMainListAdapter : ListAdapter<Spell, SpellMainListAdapter.ViewHolder>(SpellDiffUtil) {

    private var onItemClickListener: OnItemClickListener? = null
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            SpellMainItemFragmentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ViewHolder(private val binding: SpellMainItemFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.spellMainItemAction.setOnClickListener {
                if (binding.spellMainItemDescription.visibility == View.GONE) {
                    binding.spellMainItemDescription.visibility = View.VISIBLE
                } else {
                    binding.spellMainItemDescription.visibility = View.GONE
                }
            }
        }

        fun bind(item: Spell) {
            binding.run {
                spellMainItemName.text = item.spellName
                spellMainItemDescription.text = item.spellDescription
            }
        }
    }
}