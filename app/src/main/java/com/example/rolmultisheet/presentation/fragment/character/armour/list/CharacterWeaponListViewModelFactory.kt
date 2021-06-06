package com.example.rolmultisheet.presentation.fragment.character.armour.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rolmultisheet.domain.repository.AppRepository

class CharacterWeaponListViewModelFactory(
    private val appRepository: AppRepository,
    private val characterId: Long,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(CharacterWeaponListViewModel::class.java)) {
            CharacterWeaponListViewModel(appRepository, characterId) as T
        } else {
            throw IllegalArgumentException("Wrong ViewModel class passed")
        }
}