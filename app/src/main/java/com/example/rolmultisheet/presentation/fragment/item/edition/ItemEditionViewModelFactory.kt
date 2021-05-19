package com.example.rolmultisheet.presentation.fragment.item.edition

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rolmultisheet.domain.repository.AppRepository

class ItemEditionViewModelFactory(
    private val appRepository: AppRepository,
    private val spellId: Long
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(ItemEditionViewModel::class.java)) {
            ItemEditionViewModel(appRepository, spellId) as T
        } else {
            throw IllegalArgumentException("Wrong ViewModel class passed")
        }
}