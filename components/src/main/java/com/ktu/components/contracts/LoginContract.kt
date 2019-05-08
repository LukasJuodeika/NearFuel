package com.ktu.components.contracts

interface LoginContract {
    interface View{
        fun navigate(id: Int)
        fun login()
        fun displayError(message: String)
        fun displayBlankFieldsError()
        fun displayGenericError()
    }
    interface Presenter{
        fun onNavigationItemClicked(id: Int)
        fun onLoginClicked(email: String, password: String)
    }
}