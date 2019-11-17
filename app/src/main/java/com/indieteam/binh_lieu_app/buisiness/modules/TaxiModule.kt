package com.indieteam.binh_lieu_app.buisiness.modules

import com.indieteam.binh_lieu_app.buisiness.providers.TaxiProvider
import dagger.Module
import dagger.Provides

@Module
class TaxiModule {

    @Provides
    fun getTaxi(): TaxiProvider {
        return TaxiProvider()
    }
}