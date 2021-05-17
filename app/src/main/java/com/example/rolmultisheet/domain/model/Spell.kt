package com.example.rolmultisheet.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spell")
data class Spell(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "spell_id")
    val spellId: Long = 0,

    @ColumnInfo(name = "spell_name")
    val spellName: String,

    @ColumnInfo(name = "spell_cast_time")
    val spellCastTime: String?,

    @ColumnInfo(name = "spell_scope")
    val spellScope: String?,

    @ColumnInfo(name = "spell_description")
    val spellDescription: String?,
)
