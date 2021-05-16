package com.example.rolmultisheet.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.rolmultisheet.domain.model.Character
import com.example.rolmultisheet.domain.model.Race

@Dao
interface AppDao {

    // Query
    @Query("SELECT * FROM character")
    fun queryAllCharacters(): LiveData<List<Character>>

    @Query("SELECT * FROM race")
    fun queryAllRaces(): LiveData<List<Race>>

    @Query("SELECT * FROM race WHERE race_id = :raceId")
    fun queryRaceById(raceId: Long): LiveData<Race?>

    // Insert
    @Insert()
    suspend fun insertCharacter(character: Character)

    @Insert()
    suspend fun insertRace(race: Race)

    // Update
    @Update()
    suspend fun updateRace(race: Race)

    // Delete
    @Delete()
    suspend fun deleteRace(race: Race)
}