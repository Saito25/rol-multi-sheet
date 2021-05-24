package com.example.rolmultisheet.presentation.fragment.creation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rolmultisheet.R
import com.example.rolmultisheet.domain.model.Character
import com.example.rolmultisheet.domain.model.Job
import com.example.rolmultisheet.domain.model.Race
import com.example.rolmultisheet.domain.repository.AppRepository
import com.example.rolmultisheet.domain.valueObject.StringResource
import com.example.rolmultisheet.presentation.util.event.Event
import com.example.rolmultisheet.presentation.util.functions.combineWith
import kotlinx.coroutines.launch

class CreationViewModel(private val appRepository: AppRepository) : ViewModel() {

    val raceList: LiveData<List<Race>> = appRepository.queryAllRaces()
    val jobList: LiveData<List<Job>> = appRepository.queryAllJobs()
    val raceIndex: MutableLiveData<Int> = MutableLiveData(0)
    var jobIndex: MutableLiveData<Int> = MutableLiveData(0)

    val currentRace = raceList.combineWith(raceIndex) { list, position ->
        if (list != null && position != null) {
            list[position]
        } else {
            Race(0, "", 0, 0.0, 0)
        }
    }

    val currentJob = jobList.combineWith(jobIndex) { list, position ->
        if (list != null && position != null) {
            list[position]
        } else {
            Job(0, "", "", "", "")
        }
    }

    private val _onInvalidName: MutableLiveData<Event<StringResource>> = MutableLiveData()
    val onInvalidName: LiveData<Event<StringResource>>
        get() = _onInvalidName

    private val _onClose: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val onClose: LiveData<Event<Boolean>>
        get() = _onClose

    fun validateName(nameValue: String?): Boolean {
        if (nameValue.isNullOrBlank()) {
            _onInvalidName.value = Event(StringResource(R.string.form_null_blank_exception))
            return false
        }
        return true
    }

    fun save(nameValue: String) {
        val raceId = currentRace.value!!.raceId
        val jobId = currentJob.value!!.jobId
        viewModelScope.launch {
            appRepository.insertCharacter(
                Character(
                    0, raceId, jobId, nameValue
                )
            )
            _onClose.value = Event(true)
        }
    }
}
