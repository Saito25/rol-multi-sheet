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
            parentColumn = "character_id_cross_ref",
            entityColumn = "spell_id_cross_ref"
        )
    )
    val spellLists: List<Spell>
)