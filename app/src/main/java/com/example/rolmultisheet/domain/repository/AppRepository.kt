package com.example.rolmultisheet.domain.repository

import androidx.lifecycle.LiveData
import com.example.rolmultisheet.domain.model.Character
import com.example.rolmultisheet.domain.model.Job
import com.example.rolmultisheet.domain.model.Race
import com.example.rolmultisheet.domain.model.Spell

interface AppRepository {

    // Query
    fun queryAllCharacters(): LiveData<List<Character>>
    fun queryAllRaces(): LiveData<List<Race>>
    fun queryRaceById(raceId: Long): LiveData<Race?>
    fun queryAllJobs(): LiveData<List<Job>>
    fun queryJobById(jobId: Long): LiveData<Job?>
    fun queryAllSpells(): LiveData<List<Spell>>

    // Insert
    suspend fun insertCharacter(character: Character)
    suspend fun insertRace(race: Race)
    suspend fun insertJob(job: Job)
    suspend fun insertSpell(spell: Spell)

    // Update
    suspend fun updateRace(race: Race)
    suspend fun updateJob(job: Job)
    suspend fun updateSpell(spell: Spell)

    // Delete
    suspend fun deleteRace(race: Race)
    suspend fun deleteJob(job: Job)
    suspend fun deleteSpell(spell: Spell)
}