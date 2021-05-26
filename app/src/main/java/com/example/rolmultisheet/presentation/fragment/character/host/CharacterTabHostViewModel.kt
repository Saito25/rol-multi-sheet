package com.example.rolmultisheet.presentation.fragment.character.host

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rolmultisheet.domain.model.Character
import com.example.rolmultisheet.domain.repository.AppRepository

class CharacterTabHostViewModel(appRepository: AppRepository, characterId: Long) : ViewModel() {

    val character: LiveData<Character?> = appRepository.queryCharacterById(characterId)
}