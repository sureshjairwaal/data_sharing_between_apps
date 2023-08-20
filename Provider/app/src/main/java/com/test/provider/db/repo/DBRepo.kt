package com.test.provider.db.repo

import com.test.provider.db.AppDatabase
import com.test.provider.db.entities.PersonEntity
import javax.inject.Inject


interface DBRepo {
    suspend fun insertPerson(person: PersonEntity)
    suspend fun getPersons(): List<PersonEntity>
}

class DBRepoImpl @Inject constructor(
    private val appDatabase: AppDatabase
) : DBRepo {
    override suspend fun insertPerson(person: PersonEntity) {
        appDatabase.personDAO().insert(person)
    }

    override suspend fun getPersons(): List<PersonEntity> {
        return appDatabase.personDAO().fetch()
    }
}