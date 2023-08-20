package com.test.provider.db.dao

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.test.provider.db.entities.PersonEntity

@Dao
interface PersonDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(person: PersonEntity): Long?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFromContentProvider(person: PersonEntity?): Long?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateFromContentProvider(person: PersonEntity?): Int?

    @Query("SELECT * FROM person_entity")
    suspend fun fetch(): List<PersonEntity>

    @Query("SELECT * FROM person_entity")
    fun fetchCursor(): Cursor

    @Query("DELETE  FROM person_entity WHERE id=:id")
    fun deletePerson(id: String)
}