package com.indieteam.binh_lieu_app.buisiness.modules

import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides

@Module
class SFMModule(private val supportFragmentManager: FragmentManager) {

    @Provides
    fun getSFM(): FragmentManager {
        return supportFragmentManager
    }
}