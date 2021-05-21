package com.example.rolmultisheet.presentation.fragment.weapon.edition

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rolmultisheet.domain.repository.AppRepository

class WeaponEditionViewModelFactory(
    private val appRepository: AppRepository,
    private val armourId: Long,
) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(WeaponEditionViewModel::class.java)) {
            WeaponEditionViewModel(appRepository, armourId) as T
        } else {
            throw IllegalArgumentException("Wrong ViewModel class passed")
        }
}