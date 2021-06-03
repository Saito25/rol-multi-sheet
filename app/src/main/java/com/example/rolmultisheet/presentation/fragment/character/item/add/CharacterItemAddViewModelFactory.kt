package com.example.rolmultisheet.presentation.fragment.character.item.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rolmultisheet.domain.repository.AppRepository

class CharacterItemAddViewModelFactory(
    private val appRepository: AppRepository,
    private val characterIdList: LongArray,
    private val characterId: Long,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(CharacterItemAddViewModel::class.java)) {
            CharacterItemAddViewModel(appRepository, characterIdList, characterId) as T
        } else {
            throw IllegalArgumentException("Wrong ViewModel class passed")
        }
}