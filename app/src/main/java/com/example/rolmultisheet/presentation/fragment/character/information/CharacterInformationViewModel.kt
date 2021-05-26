package com.example.rolmultisheet.presentation.fragment.character.information

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.rolmultisheet.domain.model.Character
import com.example.rolmultisheet.domain.model.Job
import com.example.rolmultisheet.domain.model.Race
import com.example.rolmultisheet.domain.repository.AppRepository
import kotlinx.coroutines.launch

class CharacterInformationViewModel(
    private val appRepository: AppRepository,
    private val characterId: Long
) : ViewModel() {

    val character: LiveData<Character?> = appRepository.queryCharacterById(characterId)

    val job: LiveData<Job?> = character.switchMap {
        appRepository.queryJobById(it?.jobId ?: 0)
    }

    val race: LiveData<Race?> = character.switchMap {
        appRepository.queryRaceById(it?.raceid ?: 0)
    }

    fun updateGold(gold: Int) {
        if (character.value != null) {
            val updatedCharacter = character.value!!.copy(characterGold = gold)
            viewModelScope.launch {
                appRepository.updateCharacter(updatedCharacter)
            }
        }
    }
}