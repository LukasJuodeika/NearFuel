package com.ktu.components.contracts

interface MainContract {

    interface View{
        fun navigate(id: Int)
        fun signOut()
        fun displayEmail(email: String)
    }
    interface Presenter{
        fun onCreate()
        fun onNavigationItemClick(id : Int)
        fun onSignOutClick()
    }
}