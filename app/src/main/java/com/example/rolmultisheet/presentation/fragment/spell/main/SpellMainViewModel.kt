package com.example.rolmultisheet.presentation.fragment.spell.main

import androidx.lifecycle.ViewModel
import com.example.rolmultisheet.domain.repository.AppRepository

class SpellMainViewModel(appRepository: AppRepository) : ViewModel() {

    val spellList = appRepository.queryAllSpells()
}