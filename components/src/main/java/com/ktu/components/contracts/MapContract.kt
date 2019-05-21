package com.ktu.components.contracts

interface MapContract
{
    interface View
    {
        fun openAddStationFragment()
        fun lockDrawer()
        fun unlockDrawer()
        fun setMapSettings()
        fun startTracking()
        fun moveCamera(animate : Boolean)
    }

    interface Presenter {
        fun addStationClicked()
        fun onResume()
        fun onPause()
        fun onMapReady()
        fun onLocationResult()
        fun setFocus(value : Boolean)
    }

}