package com.indieteam.binh_lieu_app


import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.indieteam.binh_lieu_app.buisiness.providers.DBManager
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DBTest {
    @Test
    fun readCoachsTable() {
// Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val dbManager = DBManager(appContext)
        dbManager.getCoachs().forEach {
            Log.d("Coach Data", it.biensoxe)
        }
    }
}