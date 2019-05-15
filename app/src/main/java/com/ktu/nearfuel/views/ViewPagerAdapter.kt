package com.ktu.nearfuel.views

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ktu.nearfuel.views.fragments.ItemListFragment


class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int = 3

    private val tabTitles = arrayOf("Petrol", "Diesel", "Gas")



    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return ItemListFragment()
            1 -> return ItemListFragment()
            else -> return ItemListFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return tabTitles[position]
    }
}