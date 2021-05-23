package com.example.rolmultisheet.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weapon")
data class Weapon(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "weapon_id")
    val weaponId: Long = 0,

    @ColumnInfo(name = "weapon_name")
    val weaponName: String,

    @ColumnInfo(name = "weapon_damage")
    val weaponDamage: String,

    @ColumnInfo(name = "weapon_scope")
    val weaponScope: Int = 0,

    @ColumnInfo(name = "weapon_damage_type")
    val weaponDameType: String = "",

    @ColumnInfo(name = "weapon_two_hand")
    val weaponIsTwoHand: Boolean = false,

    @ColumnInfo(name = "weapon_price")
    val weaponPrice: Int = 0,

    @ColumnInfo(name = "weapon_weight")
    val weaponWeight: Double = 0.0,

    @ColumnInfo(name = "weapon_description")
    val weaponDescription: String?,
)