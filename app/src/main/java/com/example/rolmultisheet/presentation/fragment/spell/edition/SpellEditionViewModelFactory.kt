package com.example.rolmultisheet.presentation.fragment.spell.edition

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rolmultisheet.domain.repository.AppRepository

class SpellEditionViewModelFactory(
    private val appRepository: AppRepository,
    private val spellId: Long
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(SpellEditionViewModel::class.java)) {
            SpellEditionViewModel(appRepository, spellId) as T
        } else {
            throw IllegalArgumentException("Wrong ViewModel class passed")
        }
}