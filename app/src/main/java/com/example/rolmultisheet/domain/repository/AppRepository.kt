package com.example.rolmultisheet.domain.repository

import androidx.lifecycle.LiveData
import com.example.rolmultisheet.domain.model.Character
import com.example.rolmultisheet.domain.model.Race

interface AppRepository {

    // Query
    fun queryAllCharacters(): LiveData<List<Character>>
    fun queryAllRaces(): LiveData<List<Race>>
    fun queryRaceById(raceId: Long): LiveData<Race?>

    // Insert
    suspend fun insertCharacter(character: Character)
    suspend fun insertRace(race: Race)

    // Update

    // Delete
}