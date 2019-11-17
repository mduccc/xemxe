package com.indieteam.binh_lieu_app.buisiness.components

import com.indieteam.binh_lieu_app.buisiness.modules.*
import com.indieteam.binh_lieu_app.buisiness.providers.CoachProvider
import com.indieteam.binh_lieu_app.buisiness.providers.TaxiProvider
import com.indieteam.binh_lieu_app.views.DetailActivity
import com.indieteam.binh_lieu_app.views.MainActivity
import com.indieteam.binh_lieu_app.views.RecyclerAdapter
import com.indieteam.binh_lieu_app.views.TabFragment
import dagger.Component
import dagger.Module

@Component(modules = [CoachModule::class, TaxiModule::class, ContextModule::class, SFMModule::class, AdapterModule::class, ViewModelModule::class, DBModule::class])
interface AppComponent {
    fun inject(coachProvider: CoachProvider)
    fun inject(taxiProvider: TaxiProvider)
    fun inject(mainActivity: MainActivity)
    fun inject(recyclerAdapter: RecyclerAdapter)
    fun inject(tabFragment: TabFragment)
    fun inject(detailActivity: DetailActivity)
}