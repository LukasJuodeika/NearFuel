package com.ktu.components.contracts

import com.ktu.components.objects.GasStation

interface ItemListContract {

    interface View{
        fun updateList(list: List<GasStation>)
    }

    interface Presenter{
        fun loadListData()
        fun onDetach()
    }
}