package com.example.rolmultisheet.presentation.fragment.item.edition

import androidx.lifecycle.*
import com.example.rolmultisheet.R
import com.example.rolmultisheet.domain.model.Spell
import com.example.rolmultisheet.domain.model.form.SpellFormValidator
import com.example.rolmultisheet.domain.model.form.util.FormNameException
import com.example.rolmultisheet.domain.repository.AppRepository
import com.example.rolmultisheet.domain.valueObject.StringResource
import com.example.rolmultisheet.presentation.util.event.Event
import kotlinx.coroutines.launch

class ItemEditionViewModel(private val appRepository: AppRepository, private val spellId: Long) :
    ViewModel() {

    private val _spell: LiveData<Spell?> = appRepository.querySpellById(spellId)

    val spell: LiveData<Event<Spell?>> = _spell.map {
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
        castTime: String,
        duration: String,
        scope: String,
        description: String,
    ) {
        try {
            if (SpellFormValidator(name, castTime, duration, scope, description).validate()) {
                _onSaveEvent.value = Event(true)
            }
        } catch (e: FormNameException) {
            println("No se ha validado")
            _onInvalidName.value = Event(StringResource(R.string.form_null_blank_exception))
        }
    }

    fun save(
        name: String,
        castTime: String,
        duration: String,
        scope: String,
        description: String,
    ) {
        val spell = formToSpell(name, castTime, duration, scope, description)
        if (_spell.value == null) {
            saveSpell(spell)
        } else {
            updateSpell(spell)
        }
    }

    private fun formToSpell(
        name: String,
        castTime: String,
        duration: String,
        scope: String,
        description: String,
    ): Spell =
        Spell(
            spellId = spellId,
            spellName = name,
            spellCastTime = castTime,
            spellDuration = duration,
            spellScope = scope,
            spellDescription = description
        )

    private fun saveSpell(spell: Spell) {
        viewModelScope.launch {
            appRepository.insertSpell(spell)
            _onCloseEvent.value = Event(true)
        }
    }


    private fun updateSpell(race: Spell) {
        viewModelScope.launch {
            appRepository.updateSpell(race)
            _onCloseEvent.value = Event(true)
        }
    }
}