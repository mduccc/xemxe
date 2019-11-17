package com.indieteam.binh_lieu_app.buisiness.modules

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val applicationContext: Context) {

    @Provides
    fun getApplicationContext(): Context {
        return applicationContext
    }
}