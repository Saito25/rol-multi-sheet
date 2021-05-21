package com.example.rolmultisheet.presentation.fragment.weapon.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rolmultisheet.domain.repository.AppRepository

class WeaponMainViewModelFactory(private val appRepository: AppRepository) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(WeaponMainViewModel::class.java)) {
            WeaponMainViewModel(appRepository) as T
        } else {
            throw IllegalArgumentException("Wrong ViewModel class passed")
        }
}