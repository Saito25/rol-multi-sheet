package com.example.rolmultisheet.presentation.fragment.job.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rolmultisheet.domain.repository.AppRepository

class JobMainViewModelFactory(private val appRepository: AppRepository) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(JobMainViewModel::class.java)) {
            JobMainViewModel(appRepository) as T
        } else {
            throw IllegalArgumentException("Wrong ViewModel class passed")
        }
}