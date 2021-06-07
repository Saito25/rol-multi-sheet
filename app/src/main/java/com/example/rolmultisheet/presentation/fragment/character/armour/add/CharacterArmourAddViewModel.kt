package com.example.rolmultisheet.presentation.fragment.character.armour.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.selection.Selection
import com.example.rolmultisheet.domain.model.Armour
import com.example.rolmultisheet.domain.model.relation.CharacterArmourCrossRef
import com.example.rolmultisheet.domain.repository.AppRepository
import com.example.rolmultisheet.presentation.util.event.Event
import kotlinx.coroutines.launch

class CharacterArmourAddViewModel(
    private val appRepository: AppRepository,
    armourIds: LongArray,
    private val characterId: Long
) :
    ViewModel() {

    val characterArmoursList: LiveData<List<Armour>> =
        appRepository.queryAllArmourExceptIds(armourIds)

    private val _onCloseFragment: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val onCloseFragment: LiveData<Event<Boolean>>
        get() = _onCloseFragment

    fun addArmoursToCharacter(selection: Selection<Long>) {
        if (selection.isEmpty) {
            _onCloseFragment.value = Event(true)
        }
        val list =
            selection.map { traitId -> CharacterArmourCrossRef(characterId, traitId) }.toList()
        submitArmours(list)
    }

    private fun submitArmours(list: List<CharacterArmourCrossRef>) {
        viewModelScope.launch {
            appRepository.insertCharacterWithArmourList(list)
            _onCloseFragment.value = Event(true)
        }
    }
}