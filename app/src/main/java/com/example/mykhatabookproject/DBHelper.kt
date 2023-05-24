package com.example.mykhatabookproject

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract.CommonDataKinds.Note
import java.util.Locale.Category

class DBHelper(
    context: Context?,

) : SQLiteOpenHelper(context,"My KhataBook.db", null, 1) {

    var  Table_name="Transa"
    var id= "id"
    var amt="Amount"
    var category="Category"
    var note="Note"
    var isExpense="isExpense"

    override fun onCreate(db: SQLiteDatabase?) {
            var sql="CREATE TABLE $Table_name($id INTEGER PRIMARY KEY AUTOINCREMENT,$amt INTEGER,$category TEXT,$note TEXT,$isExpense INTEGER)"
            db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun addTrans(transModel: TransModel) {
        var db=writableDatabase
        var Values = ContentValues().apply {
            transModel.apply {
                put(amt,Amt)
                put(category,Category)
                put(note,Note)
                put(isExpense,IsExpense)
            }
        }
        db.insert(Table_name,null,Values)

    }

    fun getTrans(): ArrayList<TransModel> {
        var transList= ArrayList<TransModel>()
        var db=readableDatabase
        var sql="SELECT * FROM $Table_name"
        var cursor= db.rawQuery(sql,null)
        cursor.moveToFirst()

        for (i in 0..cursor.count-1) {
            var id=cursor.getInt(0)
            var amt=cursor.getInt(1)
            var category=cursor.getString(2)
            var note=cursor.getString(3)
            var isExpense=cursor.getInt(4)
            var model=TransModel(id,amt,category,note,isExpense)
            transList.add(model)
            cursor.moveToNext()

        }
        return transList

    }
    fun updateTrans(transModel: TransModel) {
        var db=writableDatabase
        var value=ContentValues().apply {
            transModel.apply {
                put(amt,Amt)
                put(category,Category)
                put(note,Note)
                put(isExpense,IsExpense)
            }
        }
        db.update(Table_name,value,"id=${transModel.id}",null)
    }

    fun deleteTrans(id:Int) {
        var db=writableDatabase
        db.delete(Table_name,"id=$id",null)
    }


}