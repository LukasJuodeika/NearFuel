package com.ktu.components.contracts

interface StationListContract {

    interface View {
        fun changeElevation(elevation: Float)
    }

    interface Presenter {
        fun sort1()
        fun sort2()
        fun sort3()
        fun sort4()
        fun onAttach()
        fun onDetach()
    }
}