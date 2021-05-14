package com.example.rolmultisheet.domain.repository

import androidx.lifecycle.LiveData
import com.example.rolmultisheet.domain.model.Character

interface AppRepository {

    // Query
    fun queryAllCharacters(): LiveData<List<Character>>

    // Insert
    suspend fun insertCharacter(character: Character)

    // Update

    // Delete
}