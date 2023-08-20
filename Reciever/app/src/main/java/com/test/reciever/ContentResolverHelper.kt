package com.test.reciever

import android.content.ContentResolver
import android.content.Context
import android.net.Uri

class ContentResolverHelper(private var mContext: Context) {

    private var contentResolver: ContentResolver = mContext.contentResolver
    val allCompanyTMRecords: ArrayList<PersonEntity>
        get() {
            val companyTMList: ArrayList<PersonEntity> = ArrayList<PersonEntity>()
            val projection = arrayOf<String>("id", "name", "age")
            val cursor = contentResolver.query(Constants.CONTENT_URI, projection, null, null, null)
            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val companyTMRecord = PersonEntity()
                    companyTMRecord.id = cursor.getInt(0)
                    companyTMRecord.name = cursor.getString(1)
                    companyTMRecord.age = cursor.getString(2)

                    companyTMList.add(companyTMRecord)
                }
            }
            return companyTMList
        }

    fun getCompanyTMRecord(id: Int): PersonEntity{
        val companyTMRecord= PersonEntity()
        val contentResolver = mContext.contentResolver
        val uri: Uri = Constants.CONTENT_URI
        val projection = arrayOf<String>("id", "name", "age")
        val selection: String = "id" + "=?"
        val selectionArgs = arrayOf(id.toString())
        val sortOrder: String? = null
        val cursor = contentResolver.query(uri, projection, selection, selectionArgs, sortOrder)
        if (cursor != null && cursor.count > 0) {
            while (cursor.moveToNext()) {
                companyTMRecord.id = cursor.getInt(0)
                companyTMRecord.name = cursor.getString(1)
                companyTMRecord.age = cursor.getString(2)
            }
        }
        return companyTMRecord
    }

    /*fun insertCompanyTMRecord(companyTMRecord: PersonEntity) {
        val contentValues = PersonEntity()
        contentValues.put(KEY_NAME, companyTMRecord.name)
        contentValues.put(KEY_EMAIL, companyTMRecord.email)
        val uri = contentResolver.insert(CONTENT_URI, contentValues)
    }

    fun deleteCompanyTMRecord(index: Long?) {
        val contentResolver = mContext.contentResolver
        val where: String = KEY_ID + "=?"
        val selectionArgs = arrayOf(index.toString())
        contentResolver.delete(CONTENT_URI, where, selectionArgs)
    }

    fun updateCompanyTMRecord(companyTMRecord: CompanyTMRecord, id: Long?) {
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, companyTMRecord.id)
        contentValues.put(KEY_NAME, companyTMRecord.name)
        contentValues.put(KEY_EMAIL, companyTMRecord.email)

        val where: String = KEY_ID.toString() + "=?"
        val selectionArgs = arrayOf(id.toString())
        val noOfRec = contentResolver.update(CONTENT_URI, contentValues, where, selectionArgs)
        Toast.makeText(mContext, "$noOfRec Record Delete successfully", Toast.LENGTH_SHORT).show()
    }*/

}