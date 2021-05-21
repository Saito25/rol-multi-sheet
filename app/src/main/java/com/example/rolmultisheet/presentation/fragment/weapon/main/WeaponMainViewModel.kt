package com.example.rolmultisheet.presentation.fragment.weapon.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rolmultisheet.domain.model.Weapon
import com.example.rolmultisheet.domain.repository.AppRepository
import com.example.rolmultisheet.presentation.util.event.Event
import kotlinx.coroutines.launch

class WeaponMainViewModel(private val appRepository: AppRepository) : ViewModel() {

    val weaponList = appRepository.queryAllWeapons()

    private val _onDeleteWeaponEvent: MutableLiveData<Event<Weapon>> = MutableLiveData()
    val onDeleteWeaponEvent: LiveData<Event<Weapon>>
        get() = _onDeleteWeaponEvent

    fun deleteWeapon(weapon: Weapon) {
        viewModelScope.launch {
            appRepository.deleteWeapon(weapon)
            _onDeleteWeaponEvent.value = Event(weapon)
        }
    }

    fun recoveryWeapon(weapon: Weapon) {
        viewModelScope.launch {
            appRepository.insertWeapon(weapon)
        }
    }
}