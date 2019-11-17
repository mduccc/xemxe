package com.indieteam.binh_lieu_app.buisiness.providers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

open class DBProvider(context: Context) : SQLiteOpenHelper(context, "binhlieu", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        try {
            var sql =
                "CREATE TABLE IF NOT EXISTS coachs (id INTEGER PRIMARY KEY, tuyenxe text, biensoxe text, giave text, soghexe text, thoigian1 text, thoigian2 text, sodienthoai text, ghichu text)"
            p0?.execSQL(sql)
            sql =
                "CREATE TABLE IF NOT EXISTS taxis (id INTEGER PRIMARY KEY, tentaxi text, sodienthoai text)"
            p0?.execSQL(sql)
            Log.d("onCreateTable", "onCreate")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("onCreateTable", e.toString())
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}