package com.ktu.components.presenters

import com.ktu.components.contracts.LoginContract

class LoginPresenter(val view: LoginContract.View) : LoginContract.Presenter {

    override fun onNavigationItemClicked(id: Int) {
        view.navigate(id)
    }
}