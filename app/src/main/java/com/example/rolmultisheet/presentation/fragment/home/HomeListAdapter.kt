package com.example.rolmultisheet.presentation.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rolmultisheet.databinding.HomeFragmentItemBinding
import com.example.rolmultisheet.domain.model.Character

object CharacterDiffUtil : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean = oldItem == newItem
}

class HomeListAdapter : ListAdapter<Character, HomeListAdapter.ViewHolder>(CharacterDiffUtil) {

//    private var onItemClickListener: OnItemClickListener? = null
//    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
//        this.onItemClickListener = onItemClickListener
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            HomeFragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ViewHolder(private val binding: HomeFragmentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Character) {
            binding.run {
                labelHomeItemName.text = item.characterName
            }
        }
    }
}