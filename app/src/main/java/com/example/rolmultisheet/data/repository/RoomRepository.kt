package com.example.rolmultisheet.data.repository

import androidx.lifecycle.LiveData
import com.example.rolmultisheet.data.dao.AppDao
import com.example.rolmultisheet.domain.model.*
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

    override fun queryAllSpells(): LiveData<List<Spell>> =
        appDao.queryAllSpells()

    override fun querySpellById(spellId: Long): LiveData<Spell?> =
        appDao.querySpellById(spellId)

    override fun queryAllItems(): LiveData<List<Item>> =
        appDao.queryAllItems()

    override fun queryItemById(itemId: Long): LiveData<Item?> =
        appDao.queryItemById(itemId)

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

    override suspend fun insertSpell(spell: Spell) {
        appDao.insertSpell(spell)
    }

    override suspend fun insertItem(item: Item) {
        appDao.insertItem(item)
    }

    // Update
    override suspend fun updateRace(race: Race) {
        appDao.updateRace(race)
    }

    override suspend fun updateJob(job: Job) {
        appDao.updateJob(job)
    }

    override suspend fun updateSpell(spell: Spell) {
        appDao.updateSpell(spell)
    }

    override suspend fun updateItem(item: Item) {
        appDao.updateItem(item)
    }

    // Delete
    override suspend fun deleteRace(race: Race) {
        appDao.deleteRace(race)
    }

    override suspend fun deleteJob(job: Job) {
        appDao.deleteJob(job)
    }

    override suspend fun deleteSpell(spell: Spell) {
        appDao.deleteSpell(spell)
    }

    override suspend fun deleteItem(item: Item) {
        appDao.deleteItem(item)
    }
}