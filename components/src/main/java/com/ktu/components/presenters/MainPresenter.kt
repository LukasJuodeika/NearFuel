package com.ktu.components.presenters

import com.ktu.components.contracts.MainContract

class MainPresenter(val view: MainContract.View): MainContract.Presenter {

    override fun onNavigationItemClick(id: Int){
        view.navigate(id)
    }
}