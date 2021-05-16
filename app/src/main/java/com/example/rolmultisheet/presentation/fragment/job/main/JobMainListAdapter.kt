package com.example.rolmultisheet.presentation.fragment.job.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rolmultisheet.databinding.CommonFragmentItemBinding
import com.example.rolmultisheet.domain.model.Job
import com.example.rolmultisheet.presentation.util.recycler.OnItemClickListener

object JobDiffUtil : DiffUtil.ItemCallback<Job>() {
    override fun areItemsTheSame(oldItem: Job, newItem: Job): Boolean =
        oldItem.jobId == newItem.jobId

    override fun areContentsTheSame(oldItem: Job, newItem: Job): Boolean = oldItem == newItem
}

class JobMainListAdapter :
    ListAdapter<Job, JobMainListAdapter.ViewHolder>(JobDiffUtil) {

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

        fun bind(item: Job) {
            binding.run {
                labelCommonItemName.text = item.jobName
            }
        }
    }
}