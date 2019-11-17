package com.indieteam.binh_lieu_app.views

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.indieteam.binh_lieu_app.App
import com.indieteam.binh_lieu_app.R
import com.indieteam.binh_lieu_app.buisiness.modules.SFMModule
import com.indieteam.binh_lieu_app.buisiness.services.EndPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_tab.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var context: Context
    @Inject
    lateinit var viewPagerAdapter: ViewPagerAdapter

    init {
        App.appModule
            .sFMModule(SFMModule(supportFragmentManager))
        App.appComponent = App.appModule.build()

        App.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout.setupWithViewPager(viewpager)

        viewPagerAdapter.listLayout.apply {
            add(TabFragment.tab_coach())
            add(TabFragment.tab_taxi())
        }

        viewpager.adapter = viewPagerAdapter
    }
}
