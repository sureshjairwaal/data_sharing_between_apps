package com.test.provider.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.provider.db.dao.PersonDAO
import com.test.provider.db.entities.PersonEntity

@Database(entities = [PersonEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun personDAO(): PersonDAO
}