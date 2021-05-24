package com.example.rolmultisheet.presentation.fragment.race.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rolmultisheet.R
import com.example.rolmultisheet.domain.model.Race
import com.example.rolmultisheet.domain.repository.AppRepository
import com.example.rolmultisheet.domain.valueObject.StringResource
import com.example.rolmultisheet.presentation.util.event.Event
import kotlinx.coroutines.launch

class RaceMainViewModel(private val appRepository: AppRepository) : ViewModel() {

    val raceList = appRepository.queryAllRaces()

    private val _onDeleteRaceEvent: MutableLiveData<Event<Race>> = MutableLiveData()
    val onDeleteRaceEvent: LiveData<Event<Race>>
        get() = _onDeleteRaceEvent

    private val _onDeleteError: MutableLiveData<Event<StringResource>> = MutableLiveData()
    val onDeleteError: LiveData<Event<StringResource>>
        get() = _onDeleteError

    fun deleteRace(race: Race) {
        viewModelScope.launch {
            try {
                appRepository.deleteRace(race)
                _onDeleteRaceEvent.value = Event(race)
            } catch (e: Exception) {
                _onDeleteError.value = Event(StringResource(R.string.race_main_delete_error))
            }
        }
    }

    fun recoveryRace(race: Race) {
        viewModelScope.launch {
            appRepository.insertRace(race)
        }
    }
}