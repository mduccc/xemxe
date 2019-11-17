package com.indieteam.binh_lieu_app.views

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(supportFragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    val listLayout = ArrayList<Fragment>()
    private val tabNames = arrayOf("Xe khách", "Taxi")

    override fun getItem(position: Int): Fragment {
        return listLayout[position]
    }

    override fun getCount(): Int {
        return listLayout.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabNames[position]
    }
}