package com.example.rolmultisheet.presentation.fragment.character.item.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.selection.Selection
import com.example.rolmultisheet.domain.model.Item
import com.example.rolmultisheet.domain.model.relation.CharacterItemCrossRef
import com.example.rolmultisheet.domain.repository.AppRepository
import com.example.rolmultisheet.presentation.util.event.Event
import kotlinx.coroutines.launch

class CharacterItemAddViewModel(
    private val appRepository: AppRepository,
    itemIds: LongArray,
    private val characterId: Long
) :
    ViewModel() {

    val characterItemsList: LiveData<List<Item>> =
        appRepository.queryAllItemsExceptIds(itemIds)

    private val _onCloseFragment: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val onCloseFragment: LiveData<Event<Boolean>>
        get() = _onCloseFragment

    fun addItemsToCharacter(selection: Selection<Long>) {
        if (selection.isEmpty) {
            _onCloseFragment.value = Event(true)
        }
        val list =
            selection.map { traitId -> CharacterItemCrossRef(characterId, traitId) }.toList()
        submitItems(list)
    }

    private fun submitItems(list: List<CharacterItemCrossRef>) {
        viewModelScope.launch {
            appRepository.insertCharacterWithItemList(list)
            _onCloseFragment.value = Event(true)
        }
    }
}