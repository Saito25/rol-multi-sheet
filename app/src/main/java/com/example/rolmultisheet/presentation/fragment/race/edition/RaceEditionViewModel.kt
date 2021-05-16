package com.example.rolmultisheet.presentation.fragment.race.edition

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.rolmultisheet.domain.model.Race
import com.example.rolmultisheet.domain.repository.AppRepository
import com.example.rolmultisheet.presentation.util.event.Event

class RaceEditionViewModel(private val appRepository: AppRepository, raceId: Long) : ViewModel() {

    private val _race: LiveData<Race?> = appRepository.queryRaceById(raceId)

    val race: LiveData<Event<Race?>> = _race.map {
        Event(it)
    }

}