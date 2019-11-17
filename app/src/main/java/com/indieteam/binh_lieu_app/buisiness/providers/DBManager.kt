package com.indieteam.binh_lieu_app.buisiness.providers

import android.content.ContentValues
import android.content.Context
import com.indieteam.binh_lieu_app.buisiness.models.CoachData
import com.indieteam.binh_lieu_app.buisiness.models.TaxiData
import java.lang.Exception

class DBManager(context: Context) : DBProvider(context) {

    private fun coachIsExist(): Boolean {
        val boolean: Boolean

        val readable = readableDatabase
        val cursor = readable.rawQuery("SELECT * FROM coachs", null)
        boolean = cursor.columnCount > 0
        readable.close()
        cursor.close()

        return boolean
    }

    private fun taxiIsExist(): Boolean {
        val boolean: Boolean

        val readable = readableDatabase
        val cursor = readable.rawQuery("SELECT * FROM taxis", null)
        boolean = cursor.columnCount > 0
        readable.close()
        cursor.close()

        return boolean
    }

    fun getCoachs(): ArrayList<CoachData> {
        val result = ArrayList<CoachData>()

        val readable = readableDatabase
        val cursor = readable.rawQuery("SELECT * FROM coachs", null)
        cursor.moveToFirst()

        while (!cursor.isAfterLast) {
            val coachData = CoachData(
                cursor.getString(cursor.getColumnIndex("id")),
                cursor.getString(cursor.getColumnIndex("tuyenxe")),
                cursor.getString(cursor.getColumnIndex("biensoxe")),
                cursor.getString(cursor.getColumnIndex("giave")),
                cursor.getString(cursor.getColumnIndex("soghexe")),
                cursor.getString(cursor.getColumnIndex("thoigian1")),
                cursor.getString(cursor.getColumnIndex("thoigian2")),
                cursor.getString(cursor.getColumnIndex("sodienthoai")),
                cursor.getString(cursor.getColumnIndex("ghichu"))
            )

            result.add(coachData)
            cursor.moveToNext()
        }
        readable.close()
        cursor.close()

        return result
    }

    fun getTaxis(): ArrayList<TaxiData> {
        val result = ArrayList<TaxiData>()

        val readable = readableDatabase
        val cursor = readable.rawQuery("SELECT * FROM taxis", null)
        cursor.moveToFirst()

        while (!cursor.isAfterLast) {
            val taxiData = TaxiData(
                cursor.getString(cursor.getColumnIndex("id")),
                cursor.getString(cursor.getColumnIndex("tentaxi")),
                cursor.getString(cursor.getColumnIndex("sodienthoai"))
            )

            result.add(taxiData)
            cursor.moveToNext()
        }
        readable.close()
        cursor.close()

        return result
    }

    fun getCoach(id: String): CoachData? {
        val readable = readableDatabase
        val cursor = readable.rawQuery("SELECT * FROM coachs WHERE id=?", arrayOf(id))
        cursor.moveToFirst()

        val result = if (cursor.columnCount > 0)
            CoachData(
                cursor.getString(cursor.getColumnIndex("id")),
                cursor.getString(cursor.getColumnIndex("tuyenxe")),
                cursor.getString(cursor.getColumnIndex("biensoxe")),
                cursor.getString(cursor.getColumnIndex("giave")),
                cursor.getString(cursor.getColumnIndex("soghexe")),
                cursor.getString(cursor.getColumnIndex("thoigian1")),
                cursor.getString(cursor.getColumnIndex("thoigian2")),
                cursor.getString(cursor.getColumnIndex("sodienthoai")),
                cursor.getString(cursor.getColumnIndex("ghichu"))
            )
        else
            null

        cursor.close()
        readable.close()

        return result
    }

    fun deleteCoachs(): Boolean {
        try {
            if (!coachIsExist())
                return true
            val writable = writableDatabase
            writable.delete("coachs", null, null)

            return true
        } catch (e: Exception) {
            return false
        }

    }

    fun deleteTaxis(): Boolean {
        try {
            if (!taxiIsExist())
                return true
            val writable = writableDatabase
            writable.delete("taxis", null, null)
            writable.close()
            return true
        } catch (e: Exception) {
            return false
        }
    }

    fun insertCoach(coachs: ArrayList<CoachData>): Boolean {
        try {
            val writable = writableDatabase
            for (coach in coachs) {
                val contentValue = ContentValues()
                contentValue.put("tuyenxe", coach.tuyenxe)
                contentValue.put("biensoxe", coach.biensoxe)
                contentValue.put("giave", coach.giave)
                contentValue.put("soghexe", coach.soghexe)
                contentValue.put("thoigian1", coach.thoigian1)
                contentValue.put("thoigian2", coach.thoigian2)
                contentValue.put("sodienthoai", coach.sodienthoai)
                contentValue.put("ghichu", coach.ghichu)

                writable.insert("coachs", null, contentValue)
            }
            return true
        } catch (e: Exception) {
            return false
        }
    }

    fun insertTaxi(taxis: ArrayList<TaxiData>): Boolean {
        try {
            val writable = writableDatabase
            for (taxi in taxis) {
                val contentValue = ContentValues()
                contentValue.put("tentaxi", taxi.tentaxi)
                contentValue.put("sodienthoai", taxi.sodienthoai)

                writable.insert("taxis", null, contentValue)
            }
            return true
        } catch (e: Exception) {
            return false
        }
    }
}