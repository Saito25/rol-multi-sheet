package com.example.rolmultisheet.presentation.fragment.character.armour.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rolmultisheet.domain.model.Armour
import com.example.rolmultisheet.domain.model.relation.CharacterArmourCrossRef
import com.example.rolmultisheet.domain.model.relation.CharacterWithArmours
import com.example.rolmultisheet.domain.repository.AppRepository
import kotlinx.coroutines.launch

class CharacterArmourListViewModel(
    private val appRepository: AppRepository,
    private val characterId: Long
) : ViewModel() {
    val characterArmoursList: LiveData<CharacterWithArmours> =
        appRepository.queryCharacterByIdWithArmoursList(characterId)

    val characterArmourIdList: LongArray
        get() = characterArmoursList.value!!.armourLists.map { armour -> armour.armourId }
            .toLongArray()

    fun deleteArmourFromCharacter(armour: Armour) {
        viewModelScope.launch {
            appRepository.deleteCharacterWithArmour(
                CharacterArmourCrossRef(characterId, armour.armourId)
            )
        }
    }
}