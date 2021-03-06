package com.example.rolmultisheet.data.repository

import androidx.lifecycle.LiveData
import com.example.rolmultisheet.data.dao.AppDao
import com.example.rolmultisheet.domain.model.*
import com.example.rolmultisheet.domain.model.relation.*
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

    override fun queryAllSpellExceptIds(spellIdList: LongArray): LiveData<List<Spell>> =
        appDao.queryAllSpellExceptIds(spellIdList)

    override fun queryAllItems(): LiveData<List<Item>> =
        appDao.queryAllItems()

    override fun queryItemById(itemId: Long): LiveData<Item?> =
        appDao.queryItemById(itemId)

    override fun queryAllItemsExceptIds(itemIdList: LongArray): LiveData<List<Item>> =
        appDao.queryAllItemsExceptIds(itemIdList)


    override fun queryAllArmours(): LiveData<List<Armour>> =
        appDao.queryAllArmours()

    override fun queryArmourById(armourId: Long): LiveData<Armour?> =
        appDao.queryArmourById(armourId)

    override fun queryAllArmourExceptIds(armourIdList: LongArray): LiveData<List<Armour>> =
        appDao.queryAllArmourExceptIds(armourIdList)

    override fun queryAllWeapons(): LiveData<List<Weapon>> =
        appDao.queryAllWeapons()

    override fun queryWeaponById(weaponId: Long): LiveData<Weapon?> =
        appDao.queryWeaponById(weaponId)

    override fun queryAllWeaponsExceptIds(weaponIdList: LongArray): LiveData<List<Weapon>> =
        appDao.queryAllWeaponsExceptIds(weaponIdList)

    override fun queryCharacterByIdWithSpellList(characterId: Long): LiveData<CharacterWithSpells> =
        appDao.queryCharacterByIdWithSpellList(characterId)

    override fun queryCharacterByIdWithItemsList(characterId: Long): LiveData<CharacterWithItems> =
        appDao.queryCharacterByIdWithItemsList(characterId)

    override fun queryCharacterByIdWithWeaponsList(characterId: Long): LiveData<CharacterWithWeapons> =
        appDao.queryCharacterByIdWithWeaponsList(characterId)

    override fun queryCharacterByIdWithArmoursList(characterId: Long): LiveData<CharacterWithArmours> =
        appDao.queryCharacterByIdWithArmoursList(characterId)


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

    override suspend fun insertCharacterWithSpell(characterSpellCrossRef: CharacterSpellCrossRef) {
        appDao.insertCharacterWithSpell(characterSpellCrossRef)
    }

    override suspend fun insertCharacterWithSpellList(characterSpellCrossRefList: List<CharacterSpellCrossRef>) {
        appDao.insertCharacterWithSpellList(characterSpellCrossRefList)
    }

    override suspend fun insertCharacterWithItem(characterItemCrossRef: CharacterItemCrossRef) {
        appDao.insertCharacterWithItem(characterItemCrossRef)
    }

    override suspend fun insertCharacterWithItemList(characterItemCrossRefList: List<CharacterItemCrossRef>) {
        appDao.insertCharacterWithItemList(characterItemCrossRefList)
    }

    override suspend fun insertCharacterWithWeapon(characterWeaponCrossRef: CharacterWeaponCrossRef) {
        appDao.insertCharacterWithWeapon(characterWeaponCrossRef)
    }

    override suspend fun insertCharacterWithWeaponList(characterWeaponCrossRefList: List<CharacterWeaponCrossRef>) {
        appDao.insertCharacterWithWeaponList(characterWeaponCrossRefList)
    }

    override suspend fun insertCharacterWithArmour(characterArmourCrossRef: CharacterArmourCrossRef) {
        appDao.insertCharacterWithArmour(characterArmourCrossRef)
    }

    override suspend fun insertCharacterWithArmourList(characterArmourCrossRefList: List<CharacterArmourCrossRef>) {
        appDao.insertCharacterWithArmourList(characterArmourCrossRefList)
    }

    // Update
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

    override suspend fun deleteCharacterWithSpell(characterSpellCrossRef: CharacterSpellCrossRef) {
        appDao.deleteCharacterWithSpell(characterSpellCrossRef)
    }

    override suspend fun deleteCharacterWithItem(characterItemCrossRef: CharacterItemCrossRef) {
        appDao.deleteCharacterWithItem(characterItemCrossRef)
    }

    override suspend fun deleteCharacterWithWeapon(characterWeaponCrossRef: CharacterWeaponCrossRef) {
        appDao.deleteCharacterWithWeapon(characterWeaponCrossRef)
    }

    override suspend fun deleteCharacterWithArmour(characterArmourCrossRef: CharacterArmourCrossRef) {
        appDao.deleteCharacterWithArmour(characterArmourCrossRef)
    }
}


