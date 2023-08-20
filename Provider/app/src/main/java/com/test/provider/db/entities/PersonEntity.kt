package com.test.provider.db.entities

import android.content.ContentValues
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_entity")
data class PersonEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int=0,
    var name: String? = "",
    var age: String? = "",
    var gender: String = GENDER.FEMALE.name
)

fun PersonEntity.fromContentValues(values : ContentValues): PersonEntity?{
    return PersonEntity(
        id = values.getAsInteger("id"),
        age = values.getAsString("age"),
        name = values.getAsString("name")
    )
}

enum class GENDER{
    MALE, FEMALE
}
