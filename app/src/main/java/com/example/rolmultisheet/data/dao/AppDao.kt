package com.example.rolmultisheet.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.rolmultisheet.domain.model.Character
import com.example.rolmultisheet.domain.model.Job
import com.example.rolmultisheet.domain.model.Race
import com.example.rolmultisheet.domain.model.Spell

@Dao
interface AppDao {

    // Query
    @Query("SELECT * FROM character")
    fun queryAllCharacters(): LiveData<List<Character>>

    @Query("SELECT * FROM race")
    fun queryAllRaces(): LiveData<List<Race>>

    @Query("SELECT * FROM race WHERE race_id = :raceId")
    fun queryRaceById(raceId: Long): LiveData<Race?>

    @Query("SELECT * FROM job")
    fun queryAllJobs(): LiveData<List<Job>>

    @Query("SELECT * FROM job WHERE job_id = :jobId")
    fun queryJobById(jobId: Long): LiveData<Job?>

    @Query("SELECT * FROM spell")
    fun queryAllSpells(): LiveData<List<Spell>>

    // Insert
    @Insert()
    suspend fun insertCharacter(character: Character)

    @Insert()
    suspend fun insertRace(race: Race)

    @Insert()
    suspend fun insertJob(job: Job)

    @Insert()
    suspend fun insertSpell(spell: Spell)

    // Update
    @Update()
    suspend fun updateRace(race: Race)

    @Update()
    suspend fun updateJob(job: Job)

    @Update()
    suspend fun updateSpell(spell: Spell)

    // Delete
    @Delete()
    suspend fun deleteRace(race: Race)

    @Delete()
    suspend fun deleteJob(job: Job)

    @Delete()
    suspend fun deleteSpell(spell: Spell)
}