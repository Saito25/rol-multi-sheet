package com.example.rolmultisheet.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "armour")
data class Armour(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "armour_id")
    val armourId: Long = 0,

    @ColumnInfo(name = "armour_name")
    val armourName: String,

    @ColumnInfo(name = "armour_class")
    val armourClass: Int = 0,

    @ColumnInfo(name = "armour_max_bonus")
    val armourMaxBonus: Int = 0,

    @ColumnInfo(name = "armour_required_min_strength")
    val armourRequiredMinStrength: Int = 0,

    @ColumnInfo(name = "armour_stealth_disadvantage")
    val armourStealthDisadvantage: Boolean = false,

    @ColumnInfo(name = "armour_price")
    val armourPrice: Int = 0,

    @ColumnInfo(name = "armour_weight")
    val armourWeight: Double = 0.0,

    @ColumnInfo(name = "armour_description")
    val armourDescription: String?,
)
