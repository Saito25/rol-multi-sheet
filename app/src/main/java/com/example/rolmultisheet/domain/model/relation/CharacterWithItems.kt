package com.example.rolmultisheet.domain.model.relation

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.example.rolmultisheet.domain.model.Character
import com.example.rolmultisheet.domain.model.Item

@Entity(tableName = "character_with_items")
data class CharacterWithItems(
    @Embedded
    val character: Character,
    @Relation(
        parentColumn = "character_id",
        entityColumn = "item_id",
        associateBy = Junction(
            value = CharacterItemCrossRef::class,
            parentColumn = "character_id_cross_ref",
            entityColumn = "item_id_cross_ref"
        )
    )
    val itemLists: List<Item>
)