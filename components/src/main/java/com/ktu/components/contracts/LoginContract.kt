package com.ktu.components.contracts

interface LoginContract {
    interface View{
        fun navigate(id: Int)
    }
    interface Presenter{
        fun onNavigationItemClicked(id: Int)
    }
}