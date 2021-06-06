package com.example.rolmultisheet.domain.model.relation

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.example.rolmultisheet.domain.model.Armour
import com.example.rolmultisheet.domain.model.Character

@Entity(tableName = "character_with_armours")
data class CharacterWithArmours(
    @Embedded
    val character: Character,
    @Relation(
        parentColumn = "character_id",
        entityColumn = "armour_id",
        associateBy = Junction(
            value = CharacterArmourCrossRef::class,
            parentColumn = "character_id_cross_ref",
            entityColumn = "armour_id_cross_ref"
        )
    )
    val armourLists: List<Armour>
)