package com.example.rolmultisheet.presentation.fragment.home

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rolmultisheet.domain.model.Character
import com.example.rolmultisheet.domain.repository.AppRepository
import kotlinx.coroutines.launch

// Todo: when it's eliminate a character, The user should be asked if they want to delete it
class HomeViewModel(private val appRepository: AppRepository) : ViewModel() {

    val characters: LiveData<List<Character>> = appRepository.queryAllCharacters()

    fun deleteCharacter(character: Character) {
        viewModelScope.launch {
            appRepository.deleteCharacter(character)
        }
    }

    fun updateCharacter(character: Character, data: Uri) {
        viewModelScope.launch {
            appRepository.updateCharacter(character.copy(characterImage = data.toString()))
        }
    }
}
