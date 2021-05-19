package com.example.rolmultisheet.presentation.fragment.item.edition

import androidx.lifecycle.*
import com.example.rolmultisheet.R
import com.example.rolmultisheet.domain.model.Item
import com.example.rolmultisheet.domain.model.form.ItemFormValidator
import com.example.rolmultisheet.domain.model.form.util.FormNameException
import com.example.rolmultisheet.domain.repository.AppRepository
import com.example.rolmultisheet.domain.valueObject.StringResource
import com.example.rolmultisheet.presentation.util.event.Event
import kotlinx.coroutines.launch

class ItemEditionViewModel(private val appRepository: AppRepository, private val itemId: Long) :
    ViewModel() {

    private val _item: LiveData<Item?> = appRepository.queryItemById(itemId)

    val item: LiveData<Event<Item?>> = _item.map {
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

    fun validateTextFields(
        name: String,
        price: String,
        weight: String,
        description: String,
    ) {
        try {
            if (ItemFormValidator(
                    name,
                    price.toIntOrNull(),
                    weight.toDoubleOrNull(),
                    description,
                ).validate()
            ) {
                _onSaveEvent.value = Event(true)
            }
        } catch (e: FormNameException) {
            println("No se ha validado")
            _onInvalidName.value = Event(StringResource(R.string.form_null_blank_exception))
        }
    }

    fun save(
        name: String,
        price: String,
        weight: String,
        description: String,
    ) {
        val item = formToItem(name, price, weight, description)
        if (_item.value == null) {
            saveItem(item)
        } else {
            updateItem(item)
        }
    }

    private fun formToItem(
        name: String,
        price: String,
        weight: String,
        description: String,
    ): Item =
        Item(
            itemId = itemId,
            itemName = name,
            itemWeight = weight.toDoubleOrNull() ?: 0.0,
            itemPrice = price.toIntOrNull() ?: 0,
            itemDescription = description
        )

    private fun saveItem(item: Item) {
        viewModelScope.launch {
            appRepository.insertItem(item)
            _onCloseEvent.value = Event(true)
        }
    }


    private fun updateItem(race: Item) {
        viewModelScope.launch {
            appRepository.updateItem(race)
            _onCloseEvent.value = Event(true)
        }
    }
}