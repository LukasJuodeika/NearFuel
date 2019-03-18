package com.ktu.components.presenters

import com.ktu.components.contracts.StationListContract

class StationListPresenter(val view: StationListContract.View): StationListContract.Presenter {

    override fun sort1() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun sort2() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun sort3() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun sort4() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAttach() {
        view.changeElevation(0F)
    }

    override fun onDetach() {
        view.changeElevation(8F)
    }

}