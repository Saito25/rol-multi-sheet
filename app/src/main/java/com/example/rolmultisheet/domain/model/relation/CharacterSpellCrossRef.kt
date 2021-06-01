package com.example.rolmultisheet.domain.model.relation

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import com.example.rolmultisheet.domain.model.Character
import com.example.rolmultisheet.domain.model.Spell

@Entity(
    primaryKeys = ["character_id_cross_ref", "spell_id_cross_ref"],
    foreignKeys = [
        ForeignKey(
            entity = Character::class,
            parentColumns = ["character_id"],
            childColumns = ["character_id_cross_ref"],
            onUpdate = CASCADE,
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = Spell::class,
            parentColumns = ["spell_id"],
            childColumns = ["spell_id_cross_ref"],
            onUpdate = CASCADE,
            onDelete = CASCADE
        )
    ]
)
data class CharacterSpellCrossRef(
    @ColumnInfo(name = "character_id_cross_ref", index = true)
    val characterIdCrossRef: Long,
    @ColumnInfo(name = "spell_id_cross_ref", index = true)
    val spellIdCrossRef: Long,
)