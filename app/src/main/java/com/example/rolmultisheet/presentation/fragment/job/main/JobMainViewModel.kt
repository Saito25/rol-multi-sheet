package com.example.rolmultisheet.presentation.fragment.job.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rolmultisheet.R
import com.example.rolmultisheet.domain.model.Job
import com.example.rolmultisheet.domain.repository.AppRepository
import com.example.rolmultisheet.domain.valueObject.StringResource
import com.example.rolmultisheet.presentation.util.event.Event
import kotlinx.coroutines.launch

class JobMainViewModel(private val appRepository: AppRepository) : ViewModel() {

    val jobList = appRepository.queryAllJobs()

    private val _onDeleteJobEvent: MutableLiveData<Event<Job>> = MutableLiveData()
    val onDeleteJobEvent: LiveData<Event<Job>>
        get() = _onDeleteJobEvent

    private val _onDeleteError: MutableLiveData<Event<StringResource>> = MutableLiveData()
    val onDeleteError: LiveData<Event<StringResource>>
        get() = _onDeleteError

    fun deleteJob(job: Job) {
        viewModelScope.launch {
            try {
                appRepository.deleteJob(job)
                _onDeleteJobEvent.value = Event(job)
            } catch (e: Exception) {
                _onDeleteError.value = Event(StringResource(R.string.job_main_delete_error))
            }
        }
    }

    fun recoveryJob(job: Job) {
        viewModelScope.launch {
            appRepository.insertJob(job)
        }
    }
}