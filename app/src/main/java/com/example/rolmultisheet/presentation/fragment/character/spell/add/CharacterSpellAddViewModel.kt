package com.example.rolmultisheet.presentation.fragment.character.spell.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rolmultisheet.domain.model.relation.CharacterWithSpells
import com.example.rolmultisheet.domain.repository.AppRepository

class CharacterSpellAddViewModel(appRepository: AppRepository, characterId: LongArray) :
    ViewModel() {

    val characterSpellsList: LiveData<CharacterWithSpells> =
        appRepository.queryCharacterByIdWithSpellList(characterId.first())
}