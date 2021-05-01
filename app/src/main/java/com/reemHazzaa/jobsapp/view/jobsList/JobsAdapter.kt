package com.reemHazzaa.jobsapp.view.jobsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.reemHazzaa.jobsapp.data.model.JobItem
import com.reemHazzaa.jobsapp.databinding.ItemJobBinding


class JobsAdapter : RecyclerView.Adapter<JobsAdapter.JobViewHolder>() {

    private var list = emptyList<JobItem>()

    class JobViewHolder(private val binding: ItemJobBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: JobItem) {
            binding.job = item
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): JobViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemJobBinding.inflate(layoutInflater, parent, false)
                return JobViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        return JobViewHolder.from(parent)
    }

    override fun getItemCount(): Int = list.size

    fun setList(filteredList: List<JobItem>) {
        val tasksDiffUtil = JobsDiffUtil(list, filteredList)
        val diffUtilResult = DiffUtil.calculateDiff(tasksDiffUtil)
        list = filteredList
        diffUtilResult.dispatchUpdatesTo(this)
    }

    fun getList(): List<JobItem> = list

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val currentItem = list[position]
        holder.bind(currentItem)
    }
}
