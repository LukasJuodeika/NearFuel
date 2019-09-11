package com.ktu.components.presenters

import com.ktu.components.contracts.MapContract

class MapPresenter(val view: MapContract.View) : MapContract.Presenter {

    private var isOutOfFocus: Boolean = false

    override fun onResume() {
        view.unlockDrawer()
        isOutOfFocus = false
    }

    override fun onPause() {
        view.lockDrawer()
    }

    override fun onMapReady() {
        view.setMapSettings()
        view.startTracking()
    }

    override fun onLocationResult() {
        if (!isOutOfFocus) view.moveCamera(false)
    }

    override fun setFocus(value: Boolean) {
        isOutOfFocus = value
    }
}