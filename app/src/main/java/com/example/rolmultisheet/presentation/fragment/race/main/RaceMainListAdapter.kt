package com.example.rolmultisheet.presentation.fragment.race.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rolmultisheet.databinding.CommonFragmentItemBinding
import com.example.rolmultisheet.domain.model.Race
import com.example.rolmultisheet.presentation.util.recycler.OnItemClickListener

object RaceDiffUtil : DiffUtil.ItemCallback<Race>() {
    override fun areItemsTheSame(oldItem: Race, newItem: Race): Boolean =
        oldItem.raceId == newItem.raceId

    override fun areContentsTheSame(oldItem: Race, newItem: Race): Boolean = oldItem == newItem
}

class RaceMainListAdapter :
    ListAdapter<Race, RaceMainListAdapter.ViewHolder>(RaceDiffUtil) {

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

        fun bind(item: Race) {
            binding.run {
                labelCommonItemName.text = item.raceName
            }
        }
    }
}