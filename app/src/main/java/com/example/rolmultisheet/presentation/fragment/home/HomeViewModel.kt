package com.example.rolmultisheet.presentation.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rolmultisheet.domain.model.Character
import com.example.rolmultisheet.domain.repository.AppRepository

class HomeViewModel(private val appRepository: AppRepository) : ViewModel() {

    val characters: LiveData<List<Character>> = appRepository.queryAllCharacters()
}