package com.ktu.components.presenters

import com.ktu.components.contracts.MapContract

class MapPresenter(val view: MapContract.View): MapContract.Presenter
{
    override fun addStationClicked() {
        view.openAddStationFragment()
    }

    override fun onResume() {
        view.unlockDrawer()
    }

    override fun onPause() {
        view.lockDrawer()
    }

}