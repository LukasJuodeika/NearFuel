package com.ktu.components.contracts

interface SignUpContract {
    interface View{
        fun navigate(id: Int)
        fun navigateToLogin()
        fun displayError(message: String)
    }
    interface Presenter{
        fun onNavigationItemClicked(id: Int)
        fun onSignUpClicked(email: String, password: String)
    }
}