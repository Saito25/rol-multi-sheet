package com.example.rolmultisheet.presentation.fragment.character.armour.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rolmultisheet.domain.repository.AppRepository

class CharacterArmourAddViewModelFactory(
    private val appRepository: AppRepository,
    private val armourIdList: LongArray,
    private val characterId: Long,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(CharacterArmourAddViewModel::class.java)) {
            CharacterArmourAddViewModel(appRepository, armourIdList, characterId) as T
        } else {
            throw IllegalArgumentException("Wrong ViewModel class passed")
        }
}