package com.example.rolmultisheet.presentation.fragment.character.host.modal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import java.util.*
import kotlin.collections.ArrayList

class DiceDialogViewModel : ViewModel() {

    private val _dices: MutableLiveData<ArrayList<Int>> = MutableLiveData(ArrayList())

    val dices: LiveData<String> = _dices.map { dices ->
        dices.sorted()
            .groupBy { number -> number }
            .map { entry -> "${entry.value.size}D${entry.key}" }
            .joinToString("+ ")
    }

    private val _throwValue: MutableLiveData<String> = MutableLiveData()
    val throwValue: LiveData<String>
        get() = _throwValue

    fun addDice(diceValue: Int) {
        _dices.value = _dices.value!!.apply { add(diceValue) }
    }

    fun getThrowValue() {
        val random = Random()
        val sumResult = _dices.value!!.map { diceValue -> random.nextInt(diceValue) + 1 }.sum()
        _throwValue.value = sumResult.toString()
    }

    fun resetThrowValue() {
        _dices.value = ArrayList()
    }
}