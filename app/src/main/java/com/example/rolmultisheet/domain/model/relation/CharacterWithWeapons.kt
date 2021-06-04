package com.example.rolmultisheet.domain.model.relation

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.example.rolmultisheet.domain.model.Character
import com.example.rolmultisheet.domain.model.Weapon

@Entity(tableName = "character_with_weapons")
data class CharacterWithWeapons(
    @Embedded
    val character: Character,
    @Relation(
        parentColumn = "character_id",
        entityColumn = "weapon_id",
        associateBy = Junction(
            value = CharacterWeaponCrossRef::class,
            parentColumn = "character_id_cross_ref",
            entityColumn = "weapon_id_cross_ref"
        )
    )
    val weaponLists: List<Weapon>
)