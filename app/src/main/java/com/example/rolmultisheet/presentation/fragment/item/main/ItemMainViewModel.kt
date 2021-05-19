package com.example.rolmultisheet.presentation.fragment.item.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rolmultisheet.domain.model.Item
import com.example.rolmultisheet.domain.repository.AppRepository
import com.example.rolmultisheet.presentation.util.event.Event
import kotlinx.coroutines.launch

class ItemMainViewModel(private val appRepository: AppRepository) : ViewModel() {

    val itemList = appRepository.queryAllItems()

    private val _onDeleteItemEvent: MutableLiveData<Event<Item>> = MutableLiveData()
    val onDeleteItemEvent: LiveData<Event<Item>>
        get() = _onDeleteItemEvent

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            appRepository.deleteItem(item)
            _onDeleteItemEvent.value = Event(item)
        }
    }

    fun recoveryItem(item: Item) {
        viewModelScope.launch {
            appRepository.insertItem(item)
        }
    }
}