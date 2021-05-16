package com.example.rolmultisheet.presentation.fragment.job.edition

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rolmultisheet.domain.repository.AppRepository

class RaceEditionViewModelFactory(
    private val appRepository: AppRepository,
    private val raceId: Long,
) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(RaceEditionViewModel::class.java)) {
            RaceEditionViewModel(appRepository, raceId) as T
        } else {
            throw IllegalArgumentException("Wrong ViewModel class passed")
        }
}