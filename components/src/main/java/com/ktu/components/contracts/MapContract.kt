package com.ktu.components.contracts

interface MapContract
{
    interface View
    {
        fun openAddStationFragment()
        fun lockDrawer()
        fun unlockDrawer()
    }

    interface Presenter {
        fun addStationClicked()
        fun onResume()
        fun onPause()
    }

}