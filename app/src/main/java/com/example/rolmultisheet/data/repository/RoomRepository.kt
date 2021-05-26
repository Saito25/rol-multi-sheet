package com.example.rolmultisheet.data.repository

import androidx.lifecycle.LiveData
import com.example.rolmultisheet.data.dao.AppDao
import com.example.rolmultisheet.domain.model.*
import com.example.rolmultisheet.domain.repository.AppRepository

class RoomRepository(private val appDao: AppDao) : AppRepository {

    // Query
    override fun queryAllCharacters(): LiveData<List<Character>> =
        appDao.queryAllCharacters()

    override fun queryCharacterById(characterId: Long): LiveData<Character?> =
        appDao.queryCharacterById(characterId)

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

    override fun queryAllArmours(): LiveData<List<Armour>> =
        appDao.queryAllArmours()

    override fun queryArmourById(armourId: Long): LiveData<Armour?> =
        appDao.queryArmourById(armourId)

    override fun queryAllWeapons(): LiveData<List<Weapon>> =
        appDao.queryAllWeapons()

    override fun queryWeaponById(weaponId: Long): LiveData<Weapon?> =
        appDao.queryWeaponById(weaponId)

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

    override suspend fun insertArmour(armour: Armour) {
        appDao.insertArmour(armour)
    }

    override suspend fun insertWeapon(weapon: Weapon) {
        appDao.insertWeapon(weapon)
    }

    override suspend fun updateCharacter(character: Character) {
        appDao.updateCharacter(character)
    }

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

    override suspend fun updateArmour(armour: Armour) {
        appDao.updateArmour(armour)
    }

    override suspend fun updateWeapon(weapon: Weapon) {
        appDao.updateWeapon(weapon)
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

    override suspend fun deleteArmour(armour: Armour) {
        appDao.deleteArmour(armour)
    }

    override suspend fun deleteWeapon(weapon: Weapon) {
        appDao.deleteWeapon(weapon)
    }

    override suspend fun deleteCharacter(character: Character) {
        appDao.deleteCharacter(character)
    }
}