package com.ktu.components.contracts

interface AuthenticationContract {

    interface View {
        fun requestPermissions()
    }

    interface Presenter {
        fun checkForPermissions()
    }
}