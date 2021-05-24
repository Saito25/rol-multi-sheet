package com.example.rolmultisheet.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// To avoid errors with the name, this class is called Job, but represent a class instead.
@Entity(tableName = "job")
data class Job(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "job_id")
    val jobId: Long = 0,
    @ColumnInfo(name = "job_name")
    val jobName: String,
    @ColumnInfo(name = "job_hit")
    val jobHit: String,
    @ColumnInfo(name = "job_feature")
    val jobFeature: String?,
    @ColumnInfo(name = "job_save_throw")
    val jobSaveThrow: String?,
) {
    override fun toString(): String = jobName
}
