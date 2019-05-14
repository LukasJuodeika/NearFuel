package com.ktu.components.presenters

import com.google.firebase.auth.FirebaseAuth
import com.ktu.components.contracts.MainContract

class MainPresenter(val view: MainContract.View, private val mAuth: FirebaseAuth): MainContract.Presenter {

    override fun onNavigationItemClick(id: Int){
        view.navigate(id)
    }

    override fun onSignOutClick() {
        mAuth.signOut()
        view.signOut()
    }

    override fun onCreate() {
        checkUser()
    }

    private fun checkUser(){
        val currentUser = mAuth.currentUser
        if(currentUser == null){
            view.signOut()
        }else{
            view.displayEmail(currentUser.email.toString())
        }
    }
}