package com.example.rolmultisheet.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.ForeignKey.RESTRICT
import androidx.room.PrimaryKey

@Entity(
    tableName = "character",
    foreignKeys = [
        ForeignKey(
            entity = Job::class,
            parentColumns = ["job_id"],
            childColumns = ["job_id"],
            onUpdate = CASCADE,
            onDelete = RESTRICT
        ),
        ForeignKey(
            entity = Race::class,
            parentColumns = ["race_id"],
            childColumns = ["race_id"],
            onUpdate = CASCADE,
            onDelete = RESTRICT
        )
    ]
)
data class Character(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "character_id")
    val characterId: Long = 0,
    @ColumnInfo(name = "job_id")
    val jobId: Long,
    @ColumnInfo(name = "race_id")
    val raceid: Long,
    @ColumnInfo(name = "character_name")
    val characterName: String,
    @ColumnInfo(name = "character_strength")
    val characterStrength: Int = 1,
    @ColumnInfo(name = "character_dexterity")
    val characterDexterity: Int = 1,
    @ColumnInfo(name = "character_constitution")
    val characterConstitution: Int = 1,
    @ColumnInfo(name = "character_intelligence")
    val characterIntelligence: Int = 1,
    @ColumnInfo(name = "character_wisdom")
    val characterWisdom: Int = 1,
    @ColumnInfo(name = "character_charisma")
    val characterCharisma: Int = 1,
)
