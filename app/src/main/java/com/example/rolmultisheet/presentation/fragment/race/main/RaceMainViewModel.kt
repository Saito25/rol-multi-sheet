package com.example.rolmultisheet.presentation.fragment.race.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rolmultisheet.domain.model.Race
import com.example.rolmultisheet.domain.repository.AppRepository
import com.example.rolmultisheet.presentation.util.event.Event
import kotlinx.coroutines.launch

class RaceMainViewModel(private val appRepository: AppRepository) : ViewModel() {

    val raceList = appRepository.queryAllRaces()

    private val _onDeleteRaceEvent: MutableLiveData<Event<Race>> = MutableLiveData()
    val onDeleteRaceEvent: LiveData<Event<Race>>
        get() = _onDeleteRaceEvent

    fun deleteRace(race: Race) {
        viewModelScope.launch {
            appRepository.deleteRace(race)
            _onDeleteRaceEvent.value = Event(race)
        }
    }

    fun recoveryRace(race: Race) {
        viewModelScope.launch {
            appRepository.insertRace(race)
        }
    }
}