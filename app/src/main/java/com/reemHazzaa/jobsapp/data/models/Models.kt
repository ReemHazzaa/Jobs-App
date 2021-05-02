package com.reemHazzaa.jobsapp.data.models

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.reemHazzaa.jobsapp.utils.Constants.Companion.TABLE_JOBS
import kotlinx.parcelize.Parcelize

data class ResponseJobs(
    val jobsList: List<JobItem?>?
)

@Entity(tableName = TABLE_JOBS)
@Parcelize
data class JobItem(
    @PrimaryKey
    @NonNull @SerializedName("id") var jobId: String = "",
    @NonNull @SerializedName("type") var jobType: String? = "",
    @NonNull @SerializedName("url") var jobUrl: String? = "",
    @NonNull @SerializedName("created_at") var createdAt: String? = "",
    @NonNull @SerializedName("company") var companyName: String? = "",
    @NonNull @SerializedName("company_url") var companyUrl: String? = "",
    @NonNull @SerializedName("location") var jobLocation: String? = "",
    @NonNull @SerializedName("title") var title: String? = "",
    @NonNull @SerializedName("description") var description: String? = "",
    @NonNull @SerializedName("how_to_apply") var howToApply: String? = "",
    @NonNull @SerializedName("company_logo") var companyLogoUrl: String? = "",
    var isFav: Boolean
) : Parcelable