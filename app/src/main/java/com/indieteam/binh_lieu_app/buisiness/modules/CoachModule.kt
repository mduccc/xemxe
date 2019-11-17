package com.indieteam.binh_lieu_app.buisiness.modules

import com.indieteam.binh_lieu_app.buisiness.providers.CoachProvider
import dagger.Module
import dagger.Provides

@Module
class CoachModule {

    @Provides
    fun getCoach(): CoachProvider {
        return CoachProvider()
    }
}