package com.ktu.nearfuel.views

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ktu.components.data.FuelType
import com.ktu.nearfuel.itemList.views.ItemListFragment


class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int = 3

    private val tabTitles = arrayOf("Petrol", "Diesel", "Gas")



    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ItemListFragment.newInstance(FuelType.PETROL)
            1 -> ItemListFragment.newInstance(FuelType.DIESEL)
            else -> ItemListFragment.newInstance(FuelType.GAS)
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return tabTitles[position]
    }
}