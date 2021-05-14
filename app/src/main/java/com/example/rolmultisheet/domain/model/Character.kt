package com.example.rolmultisheet.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class Character(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "character_id")
    val characterId: Long = 0,
    @ColumnInfo(name = "character_name")
    val characterName: String,
)
