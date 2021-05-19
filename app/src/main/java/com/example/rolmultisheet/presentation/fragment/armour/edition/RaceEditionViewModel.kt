package com.example.rolmultisheet.presentation.fragment.armour.edition

import androidx.lifecycle.*
import com.example.rolmultisheet.R
import com.example.rolmultisheet.domain.model.Race
import com.example.rolmultisheet.domain.model.form.RaceFormValidator
import com.example.rolmultisheet.domain.model.form.util.FormNameException
import com.example.rolmultisheet.domain.repository.AppRepository
import com.example.rolmultisheet.domain.valueObject.StringResource
import com.example.rolmultisheet.presentation.util.event.Event
import kotlinx.coroutines.launch

class RaceEditionViewModel(private val appRepository: AppRepository, private val raceId: Long) :
    ViewModel() {

    private val _race: LiveData<Race?> = appRepository.queryRaceById(raceId)

    val race: LiveData<Event<Race?>> = _race.map {
        Event(it)
    }

    private val _onSaveEvent: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val onSaveEvent: LiveData<Event<Boolean>>
        get() = _onSaveEvent

    private val _onCloseEvent: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val onCloseEvent: LiveData<Event<Boolean>>
        get() = _onCloseEvent

    private val _onInvalidName: MutableLiveData<Event<StringResource>> = MutableLiveData()
    val onInvalidName: LiveData<Event<StringResource>>
        get() = _onInvalidName

    fun validateTextFields(
        name: String,
        velocity: String,
        height: String,
        age: String,
    ) {
        try {
            if (RaceFormValidator(name, velocity, height, age).validate()) {
                _onSaveEvent.value = Event(true)
            }
        } catch (e: FormNameException) {
            println("No se ha validado")
            _onInvalidName.value = Event(StringResource(R.string.form_null_blank_exception))
        }
    }

    fun save(
        name: String,
        velocity: String,
        height: String,
        age: String,
    ) {
        val race = formToRace(name, velocity, height, age)
        if (_race.value == null) {
            saveRace(race)
        } else {
            updateRace(race)
        }
    }

    private fun formToRace(name: String, velocity: String, height: String, age: String): Race =
        Race(
            raceId = raceId,
            raceName = name,
            raceVelocity = velocity.toIntOrNull(),
            raceAvgHeight = height.toDoubleOrNull(),
            raceAvgAge = age.toIntOrNull()
        )

    private fun saveRace(race: Race) {
        viewModelScope.launch {
            appRepository.insertRace(race)
            _onCloseEvent.value = Event(true)
        }
    }


    private fun updateRace(race: Race) {
        viewModelScope.launch {
            appRepository.updateRace(race)
            _onCloseEvent.value = Event(true)
        }
    }
}