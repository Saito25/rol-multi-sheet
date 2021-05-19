package com.example.rolmultisheet.presentation.fragment.armour.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rolmultisheet.domain.model.Armour
import com.example.rolmultisheet.domain.repository.AppRepository
import com.example.rolmultisheet.presentation.util.event.Event
import kotlinx.coroutines.launch

class ArmourMainViewModel(private val appRepository: AppRepository) : ViewModel() {

    val armourList = appRepository.queryAllArmours()

    private val _onDeleteArmourEvent: MutableLiveData<Event<Armour>> = MutableLiveData()
    val onDeleteArmourEvent: LiveData<Event<Armour>>
        get() = _onDeleteArmourEvent

    fun deleteArmour(armour: Armour) {
        viewModelScope.launch {
            appRepository.deleteArmour(armour)
            _onDeleteArmourEvent.value = Event(armour)
        }
    }

    fun recoveryArmour(armour: Armour) {
        viewModelScope.launch {
            appRepository.insertArmour(armour)
        }
    }
}