package com.example.rolmultisheet.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.rolmultisheet.data.dao.AppDao
import com.example.rolmultisheet.domain.model.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(
    entities = [Character::class, Race::class, Job::class, Spell::class, Item::class, Armour::class, Weapon::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract val appDao: AppDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            "app_database"
                        )
                            .addCallback(object : Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    GlobalScope.launch {
                                        INSTANCE!!.appDao.insertCharacter(
                                            Character(0, "Leunam")
                                        )
                                        INSTANCE!!.appDao.insertRace(
                                            Race(0, "Elf", 20, 1.9, 1000)
                                        )
                                        INSTANCE!!.appDao.insertJob(
                                            Job(
                                                0,
                                                "Warrior",
                                                "1d6",
                                                "Strenght, Agaility",
                                                "Strenght"
                                            )
                                        )
                                        INSTANCE!!.appDao.insertSpell(
                                            Spell(
                                                0,
                                                "Invisibilidad",
                                                "2 turnos",
                                                "instantáneo",
                                                "usuario",
                                                "El usuario es invisible"
                                            )
                                        )
                                        INSTANCE!!.appDao.insertItem(
                                            Item(
                                                itemId = 0,
                                                "Cuerda",
                                                20,
                                                1.0,
                                                "Trozo de 5 metros de cuerda fina",
                                            )
                                        )
                                        INSTANCE!!.appDao.insertArmour(
                                            Armour(
                                                armourId = 0,
                                                armourName = "Armadura de mallas",
                                                armourDescription = ""
                                            )
                                        )
                                        INSTANCE!!.appDao.insertWeapon(
                                            Weapon(
                                                weaponId = 0,
                                                weaponName = "Espada",
                                                weaponDamage = "1d4",
                                                weaponIsTwoHand = false,
                                            )
                                        )
                                    }
                                }
                            })
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }

    }

}