package com.example.rolmultisheet.presentation.fragment.character.item.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rolmultisheet.domain.model.Item
import com.example.rolmultisheet.domain.model.relation.CharacterItemCrossRef
import com.example.rolmultisheet.domain.model.relation.CharacterWithItems
import com.example.rolmultisheet.domain.repository.AppRepository
import kotlinx.coroutines.launch

class CharacterItemListViewModel(
    private val appRepository: AppRepository,
    private val characterId: Long
) : ViewModel() {
    val characterItemsList: LiveData<CharacterWithItems> =
        appRepository.queryCharacterByIdWithItemsList(characterId)

    val characterItemIdList: LongArray
        get() = characterItemsList.value!!.itemLists.map { item -> item.itemId }.toLongArray()

    fun deleteItemFromCharacter(item: Item) {
        viewModelScope.launch {
            appRepository.deleteCharacterWithItem(
                CharacterItemCrossRef(characterId, item.itemId)
            )
        }
    }
}