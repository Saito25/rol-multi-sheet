package com.example.rolmultisheet.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.rolmultisheet.domain.model.Character

@Dao
interface AppDao {

    // Query
    @Query("SELECT * FROM character")
    fun queryAllCharacters(): LiveData<List<Character>>

    // Insert
    @Insert()
    suspend fun insertCharacter(character: Character)

    // Update

    // Delete
}