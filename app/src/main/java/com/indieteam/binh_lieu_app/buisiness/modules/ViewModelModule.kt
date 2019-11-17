package com.indieteam.binh_lieu_app.buisiness.modules

import com.indieteam.binh_lieu_app.viewmodels.DetailViewModel
import com.indieteam.binh_lieu_app.viewmodels.ListViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun getListViewModel(): ListViewModel {
        return ListViewModel()
    }

    @Provides
    fun getDetailViewModel(): DetailViewModel {
        return DetailViewModel()
    }
}