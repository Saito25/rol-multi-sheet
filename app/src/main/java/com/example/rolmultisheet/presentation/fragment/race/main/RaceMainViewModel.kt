package com.example.rolmultisheet.presentation.fragment.race.main

import androidx.lifecycle.ViewModel
import com.example.rolmultisheet.domain.repository.AppRepository

class RaceMainViewModel(private val appRepository: AppRepository) : ViewModel() {

    val raceList = appRepository.queryAllRaces()
}