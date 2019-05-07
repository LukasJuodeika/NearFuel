package com.ktu.components.presenters

import com.ktu.components.contracts.LoginContract

class LoginPresenter(val view: LoginContract.View) : LoginContract.Presenter {

    override fun onNavigationItemClick(id: Int){
        view.navigate(id)
    }
}