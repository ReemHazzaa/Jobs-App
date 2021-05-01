package com.reemHazzaa.jobsapp.screens.jobsList

import androidx.recyclerview.widget.DiffUtil
import com.reemHazzaa.jobsapp.data.model.JobItem

class JobsDiffUtil(
    private val oldList: List<JobItem>,
    private val newList: List<JobItem>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].jobId == newList[newItemPosition].jobId
                && oldList[oldItemPosition].jobType == newList[newItemPosition].jobType
                && oldList[oldItemPosition].jobUrl == newList[newItemPosition].jobUrl
                && oldList[oldItemPosition].createdAt == newList[newItemPosition].createdAt
                && oldList[oldItemPosition].companyName == newList[newItemPosition].companyName
                && oldList[oldItemPosition].companyUrl == newList[newItemPosition].companyUrl
                && oldList[oldItemPosition].jobLocation == newList[newItemPosition].jobLocation
                && oldList[oldItemPosition].title == newList[newItemPosition].title
                && oldList[oldItemPosition].description == newList[newItemPosition].description
                && oldList[oldItemPosition].howToApply == newList[newItemPosition].howToApply
                && oldList[oldItemPosition].companyLogoUrl == newList[newItemPosition].companyLogoUrl
                && oldList[oldItemPosition].isFav == newList[newItemPosition].isFav
    }
}