package com.example.rolmultisheet.domain.repository

import androidx.lifecycle.LiveData
import com.example.rolmultisheet.domain.model.*
import com.example.rolmultisheet.domain.model.relation.*

interface AppRepository {

    // Query
    fun queryAllCharacters(): LiveData<List<Character>>
    fun queryCharacterById(characterId: Long): LiveData<Character?>
    fun queryAllRaces(): LiveData<List<Race>>
    fun queryRaceById(raceId: Long): LiveData<Race?>
    fun queryAllJobs(): LiveData<List<Job>>
    fun queryJobById(jobId: Long): LiveData<Job?>
    fun queryAllSpells(): LiveData<List<Spell>>
    fun querySpellById(spellId: Long): LiveData<Spell?>
    fun queryAllSpellExceptIds(spellIdList: LongArray): LiveData<List<Spell>>
    fun queryAllItems(): LiveData<List<Item>>
    fun queryItemById(itemId: Long): LiveData<Item?>
    fun queryAllItemsExceptIds(itemIdList: LongArray): LiveData<List<Item>>
    fun queryAllArmours(): LiveData<List<Armour>>
    fun queryArmourById(armourId: Long): LiveData<Armour?>
    fun queryAllArmourExceptIds(armourIdList: LongArray): LiveData<List<Armour>>
    fun queryAllWeapons(): LiveData<List<Weapon>>
    fun queryAllWeaponsExceptIds(weaponIdList: LongArray): LiveData<List<Weapon>>
    fun queryWeaponById(weaponId: Long): LiveData<Weapon?>
    fun queryCharacterByIdWithSpellList(characterId: Long): LiveData<CharacterWithSpells>
    fun queryCharacterByIdWithItemsList(characterId: Long): LiveData<CharacterWithItems>
    fun queryCharacterByIdWithWeaponsList(characterId: Long): LiveData<CharacterWithWeapons>
    fun queryCharacterByIdWithArmoursList(characterId: Long): LiveData<CharacterWithArmours>

    // Insert
    suspend fun insertCharacter(character: Character)
    suspend fun insertRace(race: Race)
    suspend fun insertJob(job: Job)
    suspend fun insertSpell(spell: Spell)
    suspend fun insertItem(item: Item)
    suspend fun insertArmour(armour: Armour)
    suspend fun insertWeapon(weapon: Weapon)
    suspend fun insertCharacterWithSpell(characterSpellCrossRef: CharacterSpellCrossRef)
    suspend fun insertCharacterWithSpellList(characterSpellCrossRefList: List<CharacterSpellCrossRef>)
    suspend fun insertCharacterWithItem(characterItemCrossRef: CharacterItemCrossRef)
    suspend fun insertCharacterWithItemList(characterItemCrossRefList: List<CharacterItemCrossRef>)
    suspend fun insertCharacterWithWeapon(characterWeaponCrossRef: CharacterWeaponCrossRef)
    suspend fun insertCharacterWithWeaponList(characterWeaponCrossRefList: List<CharacterWeaponCrossRef>)
    suspend fun insertCharacterWithArmour(characterArmourCrossRef: CharacterArmourCrossRef)
    suspend fun insertCharacterWithArmourList(characterArmourCrossRefList: List<CharacterArmourCrossRef>)

    // Update
    suspend fun updateCharacter(character: Character)
    suspend fun updateRace(race: Race)
    suspend fun updateJob(job: Job)
    suspend fun updateSpell(spell: Spell)
    suspend fun updateItem(item: Item)
    suspend fun updateArmour(armour: Armour)
    suspend fun updateWeapon(weapon: Weapon)

    // Delete
    suspend fun deleteRace(race: Race)
    suspend fun deleteJob(job: Job)
    suspend fun deleteSpell(spell: Spell)
    suspend fun deleteItem(item: Item)
    suspend fun deleteArmour(armour: Armour)
    suspend fun deleteWeapon(weapon: Weapon)
    suspend fun deleteCharacter(character: Character)
    suspend fun deleteCharacterWithSpell(characterSpellCrossRef: CharacterSpellCrossRef)
    suspend fun deleteCharacterWithItem(characterItemCrossRef: CharacterItemCrossRef)
    suspend fun deleteCharacterWithWeapon(characterWeaponCrossRef: CharacterWeaponCrossRef)
    suspend fun deleteCharacterWithArmour(characterArmourCrossRef: CharacterArmourCrossRef)
}