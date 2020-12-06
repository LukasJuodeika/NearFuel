package com.ktu.nearfuel

import com.ktu.components.contracts.MainContract
import com.ktu.nearfuel.network.AuthRepository

class MainPresenter(val view: MainContract.View, val authRepository: AuthRepository) :
    MainContract.Presenter {

    override fun onNavigationItemClick(id: Int) {
        view.navigate(id)
    }

    override fun onSignOutClick() {
        authRepository.setRefreshToken("")
        authRepository.setAccessToken("")
        view.signOut()
    }

    override fun onCreate() {
        checkUser()
    }

    private fun checkUser() {
//        val currentUser = mAuth.currentUser
//        if (currentUser == null || !currentUser.isEmailVerified) {
//            view.signOut()
//        } else {
//            view.displayEmail(currentUser.email.toString())
//        }
    }
}