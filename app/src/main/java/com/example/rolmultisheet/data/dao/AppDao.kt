package com.example.rolmultisheet.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.rolmultisheet.domain.model.*

@Dao
interface AppDao {

    // Query
    @Query("SELECT * FROM character")
    fun queryAllCharacters(): LiveData<List<Character>>

    @Query("SELECT * FROM character WHERE character_id = :characterId")
    fun queryCharacterById(characterId: Long): LiveData<Character?>

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

    @Query("SELECT * FROM spell WHERE spell_id = :spellId")
    fun querySpellById(spellId: Long): LiveData<Spell?>

    @Query("SELECT * FROM item")
    fun queryAllItems(): LiveData<List<Item>>

    @Query("SELECT * FROM item WHERE item_id = :itemId")
    fun queryItemById(itemId: Long): LiveData<Item?>

    @Query("SELECT * FROM armour")
    fun queryAllArmours(): LiveData<List<Armour>>

    @Query("SELECT * FROM armour WHERE armour_id = :armourId")
    fun queryArmourById(armourId: Long): LiveData<Armour?>

    @Query("SELECT * FROM weapon")
    fun queryAllWeapons(): LiveData<List<Weapon>>

    @Query("SELECT * FROM weapon WHERE weapon_id = :weaponId")
    fun queryWeaponById(weaponId: Long): LiveData<Weapon?>

    // Insert
    @Insert
    suspend fun insertCharacter(character: Character)

    @Insert
    suspend fun insertRace(race: Race)

    @Insert
    suspend fun insertJob(job: Job)

    @Insert
    suspend fun insertSpell(spell: Spell)

    @Insert
    suspend fun insertItem(item: Item)

    @Insert
    suspend fun insertArmour(armour: Armour)

    @Insert
    suspend fun insertWeapon(weapon: Weapon)

    // Update
    @Update
    suspend fun updateCharacter(character: Character)

    @Update
    suspend fun updateRace(race: Race)

    @Update
    suspend fun updateJob(job: Job)

    @Update
    suspend fun updateSpell(spell: Spell)

    @Update
    suspend fun updateItem(item: Item)

    @Update
    suspend fun updateArmour(armour: Armour)

    @Update
    suspend fun updateWeapon(weapon: Weapon)

    // Delete
    @Delete
    suspend fun deleteRace(race: Race)

    @Delete
    suspend fun deleteJob(job: Job)

    @Delete
    suspend fun deleteSpell(spell: Spell)

    @Delete
    suspend fun deleteItem(item: Item)

    @Delete
    suspend fun deleteArmour(armour: Armour)

    @Delete
    suspend fun deleteWeapon(weapon: Weapon)

    @Delete
    suspend fun deleteCharacter(character: Character)
}