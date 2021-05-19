package com.example.rolmultisheet.presentation.fragment.job.edition

import androidx.lifecycle.*
import com.example.rolmultisheet.R
import com.example.rolmultisheet.domain.model.Job
import com.example.rolmultisheet.domain.model.form.JobFormValidator
import com.example.rolmultisheet.domain.model.form.util.FormHitDiceFormatException
import com.example.rolmultisheet.domain.model.form.util.FormNameException
import com.example.rolmultisheet.domain.repository.AppRepository
import com.example.rolmultisheet.domain.valueObject.StringResource
import com.example.rolmultisheet.presentation.util.event.Event
import kotlinx.coroutines.launch

class JobEditionViewModel(private val appRepository: AppRepository, private val jobId: Long) :
    ViewModel() {

    private val _job: LiveData<Job?> = appRepository.queryJobById(jobId)

    val job: LiveData<Event<Job?>> = _job.map {
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

    private val _onInvalidHitDice: MutableLiveData<Event<StringResource>> = MutableLiveData()
    val onInvalidHitDice: LiveData<Event<StringResource>>
        get() = _onInvalidHitDice

    fun validateTextFields(
        name: String,
        hitDice: String,
        features: String,
        saveThrows: String,
    ) {
        try {
            if (JobFormValidator(name, hitDice, features, saveThrows).validate()) {
                _onSaveEvent.value = Event(true)
            }
        } catch (e: FormNameException) {
            _onInvalidName.value = Event(StringResource(R.string.form_null_blank_exception))
        } catch (e: FormHitDiceFormatException) {
            _onInvalidHitDice.value = Event(StringResource(R.string.form_null_blank_exception))
        }
    }

    fun save(
        name: String,
        hitDice: String,
        features: String,
        saveThrows: String,
    ) {
        val job = formToJob(name, hitDice, features, saveThrows)
        if (_job.value == null) {
            saveJob(job)
        } else {
            updateJob(job)
        }
    }

    private fun formToJob(
        name: String,
        hitDice: String,
        features: String,
        saveThrows: String,
    ): Job =
        Job(
            jobId = jobId,
            jobName = name,
            jobHit = hitDice,
            jobFeature = features,
            jobSaveThrow = saveThrows,
        )

    private fun saveJob(job: Job) {
        viewModelScope.launch {
            appRepository.insertJob(job)
            _onCloseEvent.value = Event(true)
        }
    }


    private fun updateJob(job: Job) {
        viewModelScope.launch {
            appRepository.updateJob(job)
            _onCloseEvent.value = Event(true)
        }
    }
}