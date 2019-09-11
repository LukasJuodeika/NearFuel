package com.ktu.nearfuel.views

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ktu.components.data.FuelType
import com.ktu.nearfuel.itemList.views.ItemListFragment
import java.lang.IndexOutOfBoundsException


class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int = 3

    private val tabTitles = arrayOf("Petrol", "Diesel", "Gas")

    private val items = arrayListOf(
        ItemListFragment.newInstance(FuelType.PETROL),
        ItemListFragment.newInstance(FuelType.DIESEL),
        ItemListFragment.newInstance(FuelType.GAS)
    )


    override fun getItem(position: Int): Fragment {
        return items[position]
    }

    override fun getPageTitle(position: Int): CharSequence {
        return tabTitles[position]
    }
}