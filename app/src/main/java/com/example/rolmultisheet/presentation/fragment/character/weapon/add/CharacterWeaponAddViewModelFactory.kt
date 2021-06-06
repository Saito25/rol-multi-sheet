package com.example.rolmultisheet.presentation.fragment.character.weapon.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rolmultisheet.domain.repository.AppRepository

class CharacterWeaponAddViewModelFactory(
    private val appRepository: AppRepository,
    private val characterIdList: LongArray,
    private val characterId: Long,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(CharacterWeaponAddViewModel::class.java)) {
            CharacterWeaponAddViewModel(appRepository, characterIdList, characterId) as T
        } else {
            throw IllegalArgumentException("Wrong ViewModel class passed")
        }
}