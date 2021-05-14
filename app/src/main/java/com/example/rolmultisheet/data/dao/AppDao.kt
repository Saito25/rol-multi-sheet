package com.example.rolmultisheet.data.dao

import androidx.room.Insert
import com.example.rolmultisheet.domain.model.Character

interface AppDao {

    // Query

    // Insert
    @Insert()
    suspend fun insertCharacter(character: Character)

    // Update

    // Delete
}