package com.ktu.components.presenters

import com.ktu.components.contracts.AuthenticationContract

class AuthenticationPresenter(val view: AuthenticationContract.View) : AuthenticationContract.Presenter {

    override fun checkForPermissions() {
        view.requestPermissions()
    }
}