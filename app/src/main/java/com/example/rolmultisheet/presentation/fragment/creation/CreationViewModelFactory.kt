package com.example.rolmultisheet.presentation.fragment.creation

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.rolmultisheet.domain.repository.AppRepository

class CreationViewModelFactory(
    private val appRepository: AppRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null,
) :
    AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T =
        if (modelClass.isAssignableFrom(CreationViewModel::class.java)) {
            CreationViewModel(appRepository, handle) as T
        } else {
            throw IllegalArgumentException("Wrong ViewModel class passed")
        }
}