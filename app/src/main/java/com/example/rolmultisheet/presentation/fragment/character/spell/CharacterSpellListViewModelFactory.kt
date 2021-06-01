package com.example.rolmultisheet.presentation.fragment.character.spell

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rolmultisheet.domain.repository.AppRepository

class CharacterSpellListViewModelFactory(
    private val appRepository: AppRepository,
    private val characterId: Long,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(CharacterSpellListViewModel::class.java)) {
            CharacterSpellListViewModel(appRepository, characterId) as T
        } else {
            throw IllegalArgumentException("Wrong ViewModel class passed")
        }
}