package com.example.rolmultisheet.presentation.fragment.race.edition

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rolmultisheet.domain.repository.AppRepository

class RaceEditionViewModelFactory(private val appRepository: AppRepository) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(RaceEditionViewModel::class.java)) {
            RaceEditionViewModel(appRepository) as T
        } else {
            throw IllegalArgumentException("Wrong ViewModel class passed")
        }
}