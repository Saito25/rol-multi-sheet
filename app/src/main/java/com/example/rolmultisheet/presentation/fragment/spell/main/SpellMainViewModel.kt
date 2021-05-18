package com.example.rolmultisheet.presentation.fragment.spell.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rolmultisheet.domain.model.Spell
import com.example.rolmultisheet.domain.repository.AppRepository
import com.example.rolmultisheet.presentation.util.event.Event
import kotlinx.coroutines.launch

class SpellMainViewModel(private val appRepository: AppRepository) : ViewModel() {

    val spellList = appRepository.queryAllSpells()

    private val _onDeleteSpellEvent: MutableLiveData<Event<Spell>> = MutableLiveData()
    val onDeleteSpellEvent: LiveData<Event<Spell>>
        get() = _onDeleteSpellEvent

    fun deleteSpell(spell: Spell) {
        viewModelScope.launch {
            appRepository.deleteSpell(spell)
            _onDeleteSpellEvent.value = Event(spell)
        }
    }

    fun recoverySpell(spell: Spell) {
        viewModelScope.launch {
            appRepository.insertSpell(spell)
        }
    }
}