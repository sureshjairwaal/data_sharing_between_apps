package com.test.reciever

import android.content.ContentValues

data class PersonEntity(
    var id : Int=0,
    var name: String? = "",
    var age: String? = "",
    var gender: GENDER = GENDER.FEMALE
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
