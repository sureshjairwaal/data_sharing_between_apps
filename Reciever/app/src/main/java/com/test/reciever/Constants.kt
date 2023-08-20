package com.test.reciever

import android.net.Uri

object Constants {
    const val TABLE_NAME = "person_entity"
    const val PROVIDER_NAME = "com.test.provider.myProvider"
    const val URL = "content://$PROVIDER_NAME/$TABLE_NAME"
    val CONTENT_URI = Uri.parse(URL)
}