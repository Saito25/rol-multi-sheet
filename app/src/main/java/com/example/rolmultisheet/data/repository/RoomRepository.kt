package com.example.rolmultisheet.data.repository

import androidx.lifecycle.LiveData
import com.example.rolmultisheet.data.dao.AppDao
import com.example.rolmultisheet.domain.model.Character
import com.example.rolmultisheet.domain.model.Job
import com.example.rolmultisheet.domain.model.Race
import com.example.rolmultisheet.domain.repository.AppRepository

class RoomRepository(private val appDao: AppDao) : AppRepository {

    // Query
    override fun queryAllCharacters(): LiveData<List<Character>> =
        appDao.queryAllCharacters()

    override fun queryAllRaces(): LiveData<List<Race>> =
        appDao.queryAllRaces()

    override fun queryRaceById(raceId: Long): LiveData<Race?> =
        appDao.queryRaceById(raceId)

    override fun queryAllJobs(): LiveData<List<Job>> =
        appDao.queryAllJobs()

    override fun queryJobById(jobId: Long): LiveData<Job?> =
        appDao.queryJobById(jobId)

    // Insert
    override suspend fun insertCharacter(character: Character) {
        appDao.insertCharacter(character)
    }

    override suspend fun insertRace(race: Race) {
        appDao.insertRace(race)
    }

    override suspend fun insertJob(job: Job) {
        appDao.insertJob(job)
    }

    // Update
    override suspend fun updateRace(race: Race) {
        appDao.updateRace(race)
    }

    override suspend fun updateJob(job: Job) {
        appDao.updateJob(job)
    }

    // Delete
    override suspend fun deleteRace(race: Race) {
        appDao.deleteRace(race)
    }

    override suspend fun deleteJob(job: Job) {
        appDao.deleteJob(job)
    }
}