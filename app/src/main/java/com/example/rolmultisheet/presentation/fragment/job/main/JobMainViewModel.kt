package com.example.rolmultisheet.presentation.fragment.job.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rolmultisheet.domain.model.Job
import com.example.rolmultisheet.domain.repository.AppRepository
import com.example.rolmultisheet.presentation.util.event.Event
import kotlinx.coroutines.launch

class JobMainViewModel(private val appRepository: AppRepository) : ViewModel() {

    val jobList = appRepository.queryAllJobs()

    private val _onDeleteJobEvent: MutableLiveData<Event<Job>> = MutableLiveData()
    val onDeleteJobEvent: LiveData<Event<Job>>
        get() = _onDeleteJobEvent

    fun deleteJob(job: Job) {
        viewModelScope.launch {
            appRepository.deleteJob(job)
            _onDeleteJobEvent.value = Event(job)
        }
    }

    fun recoveryJob(job: Job) {
        viewModelScope.launch {
            appRepository.insertJob(job)
        }
    }
}