package com.ktu.components.contracts

interface MainContract {

    interface View{
        fun navigate(id: Int)
    }
    interface Presenter{
        fun onNavigationItemClick(id : Int)
    }
}