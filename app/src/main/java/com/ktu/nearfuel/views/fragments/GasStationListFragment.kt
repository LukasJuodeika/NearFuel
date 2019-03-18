package com.ktu.nearfuel.views.fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ktu.nearfuel.R
import com.ktu.nearfuel.views.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.gas_station_list_view.view.*

class GasStationListFragment : Fragment()
{
    lateinit var pagerAdapter: ViewPagerAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.gas_station_list_view,null)

        pagerAdapter = ViewPagerAdapter(activity!!.supportFragmentManager)
        rootView.viewpager.adapter = pagerAdapter
        rootView.tablayout.setupWithViewPager(rootView.viewpager)


        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity!!.toolbar.elevation = 0F
        }
    }

    override fun onDetach() {
        super.onDetach()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity!!.toolbar.elevation = 8F
        }

    }
}