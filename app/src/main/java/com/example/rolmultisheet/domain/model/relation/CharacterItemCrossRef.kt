package com.example.rolmultisheet.domain.model.relation

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import com.example.rolmultisheet.domain.model.Character
import com.example.rolmultisheet.domain.model.Item

@Entity(
    primaryKeys = ["character_id_cross_ref", "item_id_cross_ref"],
    foreignKeys = [
        ForeignKey(
            entity = Character::class,
            parentColumns = ["character_id"],
            childColumns = ["character_id_cross_ref"],
            onUpdate = CASCADE,
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = Item::class,
            parentColumns = ["item_id"],
            childColumns = ["item_id_cross_ref"],
            onUpdate = CASCADE,
            onDelete = CASCADE
        )
    ]
)
data class CharacterItemCrossRef(
    @ColumnInfo(name = "character_id_cross_ref", index = true)
    val characterIdCrossRef: Long,
    @ColumnInfo(name = "item_id_cross_ref", index = true)
    val itemIdCrossRef: Long,
)