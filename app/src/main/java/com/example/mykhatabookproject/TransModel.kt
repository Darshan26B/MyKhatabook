package com.example.mykhatabookproject

import android.icu.text.CaseMap.Title

class TransModel {

     var id = 0
    var Amt = 0
    lateinit var Category: String
    lateinit var Note : String
    var IsExpense = 0


    constructor(id: Int, Amt: Int, Category: String, Note: String, IsExpense: Int) {
        this.id = id
        this.Amt = Amt
        this.Category = Category
        this.Note = Note
        this.IsExpense = IsExpense
    }
}