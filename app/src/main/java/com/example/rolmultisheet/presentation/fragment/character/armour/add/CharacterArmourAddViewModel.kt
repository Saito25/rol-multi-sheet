package com.example.rolmultisheet.presentation.fragment.character.armour.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.selection.Selection
import com.example.rolmultisheet.domain.model.Weapon
import com.example.rolmultisheet.domain.model.relation.CharacterWeaponCrossRef
import com.example.rolmultisheet.domain.repository.AppRepository
import com.example.rolmultisheet.presentation.util.event.Event
import kotlinx.coroutines.launch

class CharacterArmourAddViewModel(
    private val appRepository: AppRepository,
    weaponIds: LongArray,
    private val characterId: Long
) :
    ViewModel() {

    val characterWeaponsList: LiveData<List<Weapon>> =
        appRepository.queryAllWeaponsExceptIds(weaponIds)

    private val _onCloseFragment: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val onCloseFragment: LiveData<Event<Boolean>>
        get() = _onCloseFragment

    fun addWeaponsToCharacter(selection: Selection<Long>) {
        if (selection.isEmpty) {
            _onCloseFragment.value = Event(true)
        }
        val list =
            selection.map { traitId -> CharacterWeaponCrossRef(characterId, traitId) }.toList()
        submitWeapons(list)
    }

    private fun submitWeapons(list: List<CharacterWeaponCrossRef>) {
        viewModelScope.launch {
            appRepository.insertCharacterWithWeaponList(list)
            _onCloseFragment.value = Event(true)
        }
    }
}