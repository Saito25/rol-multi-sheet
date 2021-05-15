package com.example.rolmultisheet.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.rolmultisheet.domain.model.Character
import com.example.rolmultisheet.domain.model.Race

@Dao
interface AppDao {

    // Query
    @Query("SELECT * FROM character")
    fun queryAllCharacters(): LiveData<List<Character>>

    @Query("SELECT * FROM race")
    fun queryAllRaces(): LiveData<List<Race>>

    // Insert
    @Insert()
    suspend fun insertCharacter(character: Character)

    @Insert()
    suspend fun insertRace(race: Race)

    // Update

    // Delete
}