package com.example.rolmultisheet.presentation.fragment.armour.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rolmultisheet.domain.repository.AppRepository

class ArmourMainViewModelFactory(private val appRepository: AppRepository) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(ArmourMainViewModel::class.java)) {
            ArmourMainViewModel(appRepository) as T
        } else {
            throw IllegalArgumentException("Wrong ViewModel class passed")
        }
}