package com.example.rolmultisheet.presentation.fragment.armour.edition

import androidx.lifecycle.*
import com.example.rolmultisheet.R
import com.example.rolmultisheet.domain.model.Armour
import com.example.rolmultisheet.domain.model.form.ArmourFormValidator
import com.example.rolmultisheet.domain.model.form.util.FormArmourClassFormatException
import com.example.rolmultisheet.domain.model.form.util.FormNameException
import com.example.rolmultisheet.domain.repository.AppRepository
import com.example.rolmultisheet.domain.valueObject.StringResource
import com.example.rolmultisheet.presentation.util.event.Event
import kotlinx.coroutines.launch

class ArmourEditionViewModel(private val appRepository: AppRepository, private val armourId: Long) :
    ViewModel() {

    private val _armour: LiveData<Armour?> = appRepository.queryArmourById(armourId)

    val armour: LiveData<Event<Armour?>> = _armour.map {
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

    private val _onInvalidArmourClass: MutableLiveData<Event<StringResource>> = MutableLiveData()
    val onInvalidArmourClass: LiveData<Event<StringResource>>
        get() = _onInvalidArmourClass

    fun validateTextFields(
        name: String,
        armourClass: String,
        armourMaxBonus: String,
        armourRequiredMinStrength: String,
        armourStealthDisadvantage: String,
        price: String,
        weight: String,
        description: String,
    ) {
        try {
            if (ArmourFormValidator(
                    name,
                    armourClass,
                    armourMaxBonus,
                    armourRequiredMinStrength,
                    armourStealthDisadvantage,
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
        } catch (e: FormArmourClassFormatException) {
            _onInvalidArmourClass.value =
                Event(StringResource(R.string.form_armour_class_exception))
        }
    }

    fun save(
        name: String,
        armourClass: String,
        armourMaxBonus: String,
        armourRequiredMinStrength: String,
        armourStealthDisadvantage: String,
        price: String,
        weight: String,
        description: String,
    ) {
        val armour = formToArmour(
            name,
            armourClass,
            armourMaxBonus,
            armourRequiredMinStrength,
            armourStealthDisadvantage,
            price,
            weight,
            description,
        )
        if (_armour.value == null) {
            saveArmour(armour)
        } else {
            updateArmour(armour)
        }
    }

    private fun formToArmour(
        name: String,
        armourClass: String,
        armourMaxBonus: String,
        armourRequiredMinStrength: String,
        armourStealthDisadvantage: String,
        price: String,
        weight: String,
        description: String,
    ): Armour =
        Armour(
            armourId = armourId,
            armourName = name,
            armourClass = armourClass.toIntOrNull() ?: 0,
            armourMaxBonus = armourMaxBonus.toIntOrNull() ?: 0,
            armourRequiredMinStrength = armourRequiredMinStrength.toIntOrNull() ?: 0,
            armourStealthDisadvantage = armourStealthDisadvantage.toBoolean(),
            armourPrice = price.toIntOrNull() ?: 0,
            armourWeight = weight.toDoubleOrNull() ?: 0.0,
            armourDescription = description,
        )

    private fun saveArmour(armour: Armour) {
        viewModelScope.launch {
            appRepository.insertArmour(armour)
            _onCloseEvent.value = Event(true)
        }
    }


    private fun updateArmour(armour: Armour) {
        viewModelScope.launch {
            appRepository.updateArmour(armour)
            _onCloseEvent.value = Event(true)
        }
    }
}