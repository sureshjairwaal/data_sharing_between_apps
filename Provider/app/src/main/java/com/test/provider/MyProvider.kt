package com.test.provider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import androidx.room.Room
import com.test.provider.db.AppDatabase
import com.test.provider.db.dao.PersonDAO
import com.test.provider.db.entities.PersonEntity
import com.test.provider.db.entities.fromContentValues
import javax.annotation.Nullable

class MyProvider : ContentProvider() {
    companion object {
        const val AUTHORITY = "com.test.provider.myProvider"

        private const val CODE_DIR= 1
        private val MATCHER = UriMatcher(UriMatcher.NO_MATCH)

        init {
            MATCHER.addURI(
                AUTHORITY,
                "person_entity",
                CODE_DIR
            )
        }

    }
    val appDatabase by lazy {
        context?.let {
            Room.databaseBuilder(
                it,
                AppDatabase::class.java,
                "MyDataBase"
            ).fallbackToDestructiveMigration().build()
        }
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String?>?
    ): Int {
        return when (MATCHER.match(uri)) {
            CODE_DIR-> {
                val context = context ?: return 0
                val companyTMRecord: PersonEntity? = values?.let { PersonEntity().fromContentValues(it) }
                val count: Int? = appDatabase?.personDAO()?.updateFromContentProvider(companyTMRecord)
                context.contentResolver.notifyChange(uri, null)
                count ?: 0
            }
            else -> throw java.lang.IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun delete(
        uri: Uri, selection: String?,
        selectionArgs: Array<String?>?
    ): Int {
        return when (MATCHER.match(uri)) {
            CODE_DIR-> {
                val context = context ?: return 0
                appDatabase?.personDAO()?.deletePerson(selectionArgs?.get(0) ?: "1")
                context.contentResolver.notifyChange(uri, null)
                 0
            }
            else -> throw java.lang.IllegalArgumentException("Unknown URI: $uri")
        }
    }


    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return when (MATCHER.match(uri)) {
            CODE_DIR-> {
                val context = context ?: return null
                val id: Long? = appDatabase?.personDAO()?.insertFromContentProvider(values?.let { PersonEntity().fromContentValues(it) })
                context.contentResolver.notifyChange(uri, null)
                ContentUris.withAppendedId(uri, id ?:0)
            }
            else -> throw java.lang.IllegalArgumentException("Unknown URI: $uri")
        }
    }

    @Nullable
    override fun query(
        uri: Uri, @Nullable projection: Array<String>?, @Nullable selection: String?,
        @Nullable selectionArgs: Array<String>?, @Nullable sortOrder: String?
    ): Cursor? {

        val code = MATCHER.match(uri)
        return if (code == CODE_DIR) {
            val context = context ?: return null
            val companyTMDao: PersonDAO? = appDatabase?.personDAO()
            val cursor: Cursor? = if (code == CODE_DIR) {
                companyTMDao?.fetchCursor()
            } else {
                companyTMDao?.fetchCursor()
            }
            cursor?.setNotificationUri(context.contentResolver, uri)
            cursor
        } else {
            throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    @Nullable
    override fun getType(uri: Uri): String? {
        return when (MATCHER.match(uri)) {
            CODE_DIR -> "vnd.android.cursor.dir/$AUTHORITY.person_entity"
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }
}