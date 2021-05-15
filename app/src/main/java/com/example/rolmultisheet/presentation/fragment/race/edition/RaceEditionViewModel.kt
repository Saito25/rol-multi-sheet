package com.example.rolmultisheet.presentation.fragment.race.edition

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rolmultisheet.domain.model.Race
import com.example.rolmultisheet.domain.repository.AppRepository

class RaceEditionViewModel(private val appRepository: AppRepository, raceId: Long) : ViewModel() {

    val race: LiveData<Race?> = appRepository.queryRaceById(raceId)
}