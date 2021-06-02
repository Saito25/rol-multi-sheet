package com.example.rolmultisheet.presentation.fragment.character.spell.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.selection.Selection
import com.example.rolmultisheet.domain.model.Spell
import com.example.rolmultisheet.domain.model.relation.CharacterSpellCrossRef
import com.example.rolmultisheet.domain.repository.AppRepository
import com.example.rolmultisheet.presentation.util.event.Event
import kotlinx.coroutines.launch

class CharacterSpellAddViewModel(
    private val appRepository: AppRepository,
    spellIds: LongArray,
    private val characterId: Long
) :
    ViewModel() {

    val characterSpellsList: LiveData<List<Spell>> =
        appRepository.queryAllSpellExceptIds(spellIds)

    private val _onCloseFragment: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val onCloseFragment: LiveData<Event<Boolean>>
        get() = _onCloseFragment

    fun addSpellsToCharacter(selection: Selection<Long>) {
        if (selection.isEmpty) {
            _onCloseFragment.value = Event(true)
        }
        val list =
            selection.map { traitId -> CharacterSpellCrossRef(characterId, traitId) }.toList()
        submitSpells(list)
    }

    private fun submitSpells(list: List<CharacterSpellCrossRef>) {
        viewModelScope.launch {
            appRepository.insertCharacterWithSpellList(list)
            _onCloseFragment.value = Event(true)
        }
    }
}