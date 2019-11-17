package com.indieteam.binh_lieu_app.buisiness.modules

import android.content.Context
import com.indieteam.binh_lieu_app.buisiness.providers.DBManager
import dagger.Module
import dagger.Provides

@Module
class DBModule {

    @Provides
    fun getDatabaseManager(context: Context): DBManager {
        return DBManager(context)
    }
}