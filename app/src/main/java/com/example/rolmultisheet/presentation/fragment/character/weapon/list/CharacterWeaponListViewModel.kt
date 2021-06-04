package com.example.rolmultisheet.presentation.fragment.character.weapon.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rolmultisheet.domain.model.Weapon
import com.example.rolmultisheet.domain.model.relation.CharacterWeaponCrossRef
import com.example.rolmultisheet.domain.model.relation.CharacterWithWeapons
import com.example.rolmultisheet.domain.repository.AppRepository
import kotlinx.coroutines.launch

class CharacterWeaponListViewModel(
    private val appRepository: AppRepository,
    private val characterId: Long
) : ViewModel() {
    val characterWeaponsList: LiveData<CharacterWithWeapons> =
        appRepository.queryCharacterByIdWithWeaponsList(characterId)

    val characterWeaponIdList: LongArray
        get() = characterWeaponsList.value!!.weaponLists.map { weapon -> weapon.weaponId }
            .toLongArray()

    fun deleteWeaponFromCharacter(weapon: Weapon) {
        viewModelScope.launch {
            appRepository.deleteCharacterWithWeapon(
                CharacterWeaponCrossRef(characterId, weapon.weaponId)
            )
        }
    }
}