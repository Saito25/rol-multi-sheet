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
            updateCharacter(updatedCharacter)
        }
    }

    fun updateHealth(currentHealth: Int, maxHealth: Int) {
        val updatedCharacter = character.value!!.copy(
            characterCurrentLife = currentHealth,
            characterMaxLife = maxHealth
        )
        updateCharacter(updatedCharacter)
    }

    fun decrementHealth() {
        if (character.value!!.characterCurrentLife > 0) {
            val updatedCharacter = character.value!!.copy(
                characterCurrentLife = character.value!!.characterCurrentLife - 1,
            )
            updateCharacter(updatedCharacter)
        }
    }

    fun incrementHealth() {
        val updatedCharacter = character.value!!.copy(
            characterCurrentLife = character.value!!.characterCurrentLife + 1,
        )
        updateCharacter(updatedCharacter)

    }

    fun incrementArmourClass() {
        val updatedCharacter = character.value!!.copy(
            characterArmourClass = character.value!!.characterArmourClass + 1,
        )
        updateCharacter(updatedCharacter)
    }

    fun decrementArmourClass() {
        if (character.value!!.characterArmourClass > 0) {
            val updatedCharacter = character.value!!.copy(
                characterArmourClass = character.value!!.characterArmourClass - 1,
            )
            updateCharacter(updatedCharacter)
        }
    }

    fun updateStrength(strengthValue: Int) {
        val updatedCharacter = character.value!!.copy(
            characterStrength = strengthValue,
        )
        updateCharacter(updatedCharacter)
    }

    fun updateDexterity(dexterityValue: Int) {

    }

    fun updateConstitution(constitutionValue: Int) {

    }

    fun updateIntelligence(dexterityValue: Int) {

    }

    fun updateWisdom(intelligenceValue: Int) {

    }

    fun updateCharisma(charismaValue: Int) {

    }


    private fun updateCharacter(character: Character) {
        viewModelScope.launch {
            appRepository.updateCharacter(character)
        }
    }

}