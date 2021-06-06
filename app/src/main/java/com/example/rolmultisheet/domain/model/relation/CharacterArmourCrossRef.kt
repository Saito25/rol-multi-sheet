package com.example.rolmultisheet.domain.model.relation

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import com.example.rolmultisheet.domain.model.Armour
import com.example.rolmultisheet.domain.model.Character

@Entity(
    primaryKeys = ["character_id_cross_ref", "armour_id_cross_ref"],
    foreignKeys = [
        ForeignKey(
            entity = Character::class,
            parentColumns = ["character_id"],
            childColumns = ["character_id_cross_ref"],
            onUpdate = CASCADE,
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = Armour::class,
            parentColumns = ["armour_id"],
            childColumns = ["armour_id_cross_ref"],
            onUpdate = CASCADE,
            onDelete = CASCADE
        )
    ]
)
data class CharacterArmourCrossRef(
    @ColumnInfo(name = "character_id_cross_ref", index = true)
    val characterIdCrossRef: Long,
    @ColumnInfo(name = "armour_id_cross_ref", index = true)
    val armourIdCrossRef: Long,
)