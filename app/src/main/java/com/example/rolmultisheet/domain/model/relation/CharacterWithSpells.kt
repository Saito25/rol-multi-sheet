package com.example.rolmultisheet.domain.model.relation

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.example.rolmultisheet.domain.model.Character
import com.example.rolmultisheet.domain.model.Spell

@Entity(tableName = "character_with_spells")
data class CharacterWithSpells(
    @Embedded
    val character: Character,
    @Relation(
        parentColumn = "character_id",
        entityColumn = "spell_id",
        associateBy = Junction(
            value = CharacterSpellCrossRef::class,
            parentColumn = "race_id_cross_ref",
            entityColumn = "trait_id_cross_ref"
        )
    )
    val spellLists: List<Spell>
)

/*
@Entity(tableName = "race_with_traits")
data class RaceWithTraits(
    @Embedded val race: Race,
    @Relation(
        parentColumn = "race_id",
        entityColumn = "trait_id",
        associateBy = Junction(
            RaceTraitCrossRef::class,
            parentColumn = "race_id_cross_ref",
            entityColumn = "trait_id_cross_ref"
        )
    )
    val traits: List<Trait>

)
 */