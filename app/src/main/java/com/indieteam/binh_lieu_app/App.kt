package com.indieteam.binh_lieu_app

import android.app.Application
import com.indieteam.binh_lieu_app.buisiness.components.AppComponent
import com.indieteam.binh_lieu_app.buisiness.components.DaggerAppComponent
import com.indieteam.binh_lieu_app.buisiness.modules.*

class App : Application() {
    companion object {
        lateinit var appComponent: AppComponent
        lateinit var appModule: DaggerAppComponent.Builder
    }

    private fun initDefault() {
        appModule = DaggerAppComponent.builder()
            .coachModule(CoachModule())
            .taxiModule(TaxiModule())
            .contextModule(ContextModule(applicationContext))
            .viewModelModule(ViewModelModule())
            .adapterModule(AdapterModule())
    }

    override fun onCreate() {
        super.onCreate()
        initDefault()
    }
}