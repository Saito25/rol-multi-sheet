package com.example.rolmultisheet.data.repository

import androidx.lifecycle.LiveData
import com.example.rolmultisheet.data.dao.AppDao
import com.example.rolmultisheet.domain.model.Character
import com.example.rolmultisheet.domain.model.Race
import com.example.rolmultisheet.domain.repository.AppRepository

class RoomRepository(private val appDao: AppDao) : AppRepository {

    // Query
    override fun queryAllCharacters(): LiveData<List<Character>> =
        appDao.queryAllCharacters()

    override fun queryAllRaces(): LiveData<List<Race>> =
        appDao.queryAllRaces()


    // Insert
    override suspend fun insertCharacter(character: Character) {
        appDao.insertCharacter(character)
    }

    override suspend fun insertRace(race: Race) {
        appDao.insertRace(race)
    }

    // Update

    // Delete
}