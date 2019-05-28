package com.ktu.components.contracts

interface LoginContract {
    interface View{
        fun navigate(id: Int)
        fun login()
        fun displayError(message: String)
        fun displayBlankFieldError()
        fun displayGenericError()
        fun displayEmailVerificationError()
        fun showProgress()
        fun hideProgress()
    }
    interface Presenter{
        fun onNavigationItemClicked(id: Int)
        fun onLoginClicked(email: String, password: String)
    }
}