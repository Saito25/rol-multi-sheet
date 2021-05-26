package com.example.rolmultisheet.presentation.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rolmultisheet.databinding.CommonFragmentItemBinding
import com.example.rolmultisheet.domain.model.Character
import com.example.rolmultisheet.presentation.util.recycler.OnItemClickListener

object CharacterDiffUtil : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean = oldItem == newItem
}

class HomeListAdapter : ListAdapter<Character, HomeListAdapter.ViewHolder>(CharacterDiffUtil) {

    private var onItemClickListener: OnItemClickListener? = null
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            CommonFragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ViewHolder(private val binding: CommonFragmentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onItemClickListener?.onItemClick(adapterPosition)
            }
        }

        fun bind(item: Character) {
            binding.run {
                labelCommonItemName.text = item.characterName
            }
        }
    }
}