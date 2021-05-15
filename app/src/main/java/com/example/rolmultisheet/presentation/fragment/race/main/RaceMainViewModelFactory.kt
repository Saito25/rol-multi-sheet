package com.example.rolmultisheet.presentation.fragment.race.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rolmultisheet.domain.repository.AppRepository

class RaceMainViewModelFactory(private val appRepository: AppRepository) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(RaceMainViewModel::class.java)) {
            RaceMainViewModel(appRepository) as T
        } else {
            throw IllegalArgumentException("Wrong ViewModel class passed")
        }
}