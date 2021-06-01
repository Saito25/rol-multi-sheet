package com.example.rolmultisheet.presentation.fragment.character.spell

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rolmultisheet.domain.model.relation.CharacterWithSpells
import com.example.rolmultisheet.domain.repository.AppRepository

class CharacterSpellListViewModel(appRepository: AppRepository, characterId: Long) : ViewModel() {

    val characterSpellsList: LiveData<CharacterWithSpells> =
        appRepository.queryCharacterByIdWithSpellList(characterId)
}