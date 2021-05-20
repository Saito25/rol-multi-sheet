package com.example.rolmultisheet.presentation.fragment.armour.edition

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rolmultisheet.domain.repository.AppRepository

class ArmourEditionViewModelFactory(
    private val appRepository: AppRepository,
    private val raceId: Long,
) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(ArmourEditionViewModel::class.java)) {
            ArmourEditionViewModel(appRepository, raceId) as T
        } else {
            throw IllegalArgumentException("Wrong ViewModel class passed")
        }
}