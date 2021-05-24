package com.example.rolmultisheet.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "race")
data class Race(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "race_id")
    val raceId: Long = 0,

    @ColumnInfo(name = "race_name")
    val raceName: String,

    @ColumnInfo(name = "race_velocity")
    val raceVelocity: Int?,

    @ColumnInfo(name = "race_avg_height")
    val raceAvgHeight: Double?,

    @ColumnInfo(name = "race_avg_age")
    val raceAvgAge: Int?,
) {
    override fun toString(): String = raceName
}
