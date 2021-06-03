package com.example.rolmultisheet.presentation.fragment.character.item.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rolmultisheet.domain.model.Spell
import com.example.rolmultisheet.domain.model.relation.CharacterSpellCrossRef
import com.example.rolmultisheet.domain.model.relation.CharacterWithSpells
import com.example.rolmultisheet.domain.repository.AppRepository
import kotlinx.coroutines.launch

class CharacterItemListViewModel(
    private val appRepository: AppRepository,
    private val characterId: Long
) : ViewModel() {
    val characterSpellsList: LiveData<CharacterWithSpells> =
        appRepository.queryCharacterByIdWithSpellList(characterId)

    val characterSpellIdList: LongArray
        get() = characterSpellsList.value!!.spellLists.map { spell -> spell.spellId }.toLongArray()

    fun deleteSpellFromCharacter(spell: Spell) {
        viewModelScope.launch {
            appRepository.deleteCharacterWithSpell(
                CharacterSpellCrossRef(characterId, spell.spellId)
            )
        }
    }
}