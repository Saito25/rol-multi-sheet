package com.example.rolmultisheet.domain.model.relation

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import com.example.rolmultisheet.domain.model.Character
import com.example.rolmultisheet.domain.model.Weapon

@Entity(
    primaryKeys = ["character_id_cross_ref", "weapon_id_cross_ref"],
    foreignKeys = [
        ForeignKey(
            entity = Character::class,
            parentColumns = ["character_id"],
            childColumns = ["character_id_cross_ref"],
            onUpdate = CASCADE,
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = Weapon::class,
            parentColumns = ["weapon_id"],
            childColumns = ["weapon_id_cross_ref"],
            onUpdate = CASCADE,
            onDelete = CASCADE
        )
    ]
)
data class CharacterWeaponCrossRef(
    @ColumnInfo(name = "character_id_cross_ref", index = true)
    val characterIdCrossRef: Long,
    @ColumnInfo(name = "weapon_id_cross_ref", index = true)
    val weaponIdCrossRef: Long,
)