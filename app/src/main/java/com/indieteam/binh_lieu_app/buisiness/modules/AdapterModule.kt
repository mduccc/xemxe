package com.indieteam.binh_lieu_app.buisiness.modules

import androidx.fragment.app.FragmentManager
import com.indieteam.binh_lieu_app.views.ViewPagerAdapter
import dagger.Module
import dagger.Provides

@Module
class AdapterModule {

    @Provides
    fun getViewPagerAdapter(supportFragmentManager: FragmentManager): ViewPagerAdapter {
        return ViewPagerAdapter(supportFragmentManager)
    }
}