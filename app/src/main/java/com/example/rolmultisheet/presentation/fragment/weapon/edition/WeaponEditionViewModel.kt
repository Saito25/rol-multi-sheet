package com.example.rolmultisheet.presentation.fragment.weapon.edition

import androidx.lifecycle.*
import com.example.rolmultisheet.R
import com.example.rolmultisheet.domain.model.Weapon
import com.example.rolmultisheet.domain.model.form.WeaponFormValidator
import com.example.rolmultisheet.domain.model.form.util.FormNameException
import com.example.rolmultisheet.domain.model.form.util.FormWeaponDamageFormatException
import com.example.rolmultisheet.domain.repository.AppRepository
import com.example.rolmultisheet.domain.valueObject.StringResource
import com.example.rolmultisheet.presentation.util.event.Event
import kotlinx.coroutines.launch

class WeaponEditionViewModel(private val appRepository: AppRepository, private val weaponId: Long) :
    ViewModel() {

    private val _weapon: LiveData<Weapon?> = appRepository.queryWeaponById(weaponId)

    val weapon: LiveData<Event<Weapon?>> = _weapon.map {
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

    private val _onInvalidDamage: MutableLiveData<Event<StringResource>> = MutableLiveData()
    val onInvalidDamage: LiveData<Event<StringResource>>
        get() = _onInvalidDamage

    fun validateTextFields(
        name: String,
        weaponDamage: String,
        weaponScope: String,
        weaponDamageType: String,
        weaponIsTwoHand: String,
        price: String,
        weight: String,
        description: String,
    ) {
        try {
            if (WeaponFormValidator(
                    name,
                    weaponDamage,
                    weaponScope,
                    weaponDamageType,
                    weaponIsTwoHand,
                    price,
                    weight,
                    description,
                ).validate()
            ) {
                _onSaveEvent.value = Event(true)
            }
        } catch (e: FormNameException) {
            println("No se ha validado")
            _onInvalidName.value = Event(StringResource(R.string.form_null_blank_exception))
        } catch (e: FormWeaponDamageFormatException) {
            _onInvalidDamage.value =
                Event(StringResource(R.string.form_null_blank_exception))
        } catch (e: Exception) {
            println("Falló" + e.message)
        }
    }

    fun save(
        name: String,
        weaponDamage: String,
        weaponScope: String,
        weaponDamageType: String,
        weaponIsTwoHand: String,
        price: String,
        weight: String,
        description: String,
    ) {
        val weapon = formToWeapon(
            name,
            weaponDamage,
            weaponScope,
            weaponDamageType,
            weaponIsTwoHand,
            price,
            weight,
            description,
        )
        if (_weapon.value == null) {
            saveWeapon(weapon)
        } else {
            updateWeapon(weapon)
        }
    }

    private fun formToWeapon(
        name: String,
        weaponDamage: String,
        weaponScope: String,
        weaponDamageType: String,
        weaponIsTwoHand: String,
        price: String,
        weight: String,
        description: String,
    ): Weapon =
        Weapon(
            weaponId = weaponId,
            weaponName = name,
            weaponDamage = weaponDamage,
            weaponScope = weaponScope.toIntOrNull() ?: 0,
            weaponDameType = weaponDamageType,
            weaponIsTwoHand = weaponIsTwoHand.toBoolean(),
            weaponPrice = price.toIntOrNull() ?: 0,
            weaponWeight = weight.toDoubleOrNull() ?: 0.0,
            weaponDescription = description,
        )

    private fun saveWeapon(weapon: Weapon) {
        viewModelScope.launch {
            appRepository.insertWeapon(weapon)
            _onCloseEvent.value = Event(true)
        }
    }


    private fun updateWeapon(weapon: Weapon) {
        println("Entra en actualizar")
        viewModelScope.launch {
            appRepository.updateWeapon(weapon)
            _onCloseEvent.value = Event(true)
        }
    }
}