package com.ktu.components.contracts

import com.ktu.components.objects.GasStation

interface MapContract
{
    interface View {
        fun openAddStationFragment(gasStation: GasStation?)
        fun lockDrawer()
        fun unlockDrawer()
        fun setMapSettings()
        fun startTracking()
        fun moveCamera(animate : Boolean)
    }

    interface Presenter {
        fun onResume()
        fun onPause()
        fun onMapReady()
        fun onLocationResult()
        fun setFocus(value : Boolean)
    }

}