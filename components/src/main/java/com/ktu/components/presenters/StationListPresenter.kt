package com.ktu.components.presenters

import com.ktu.components.contracts.StationListContract

class StationListPresenter(val view: StationListContract.View) : StationListContract.Presenter {

    override fun sort1() {
    }

    override fun sort2() {
    }

    override fun sort3() {
    }

    override fun sort4() {
    }

    override fun onAttach() {
        view.changeElevation(0F)
    }

    override fun onDetach() {
        view.changeElevation(8F)
    }

}