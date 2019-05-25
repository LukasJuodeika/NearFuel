package com.ktu.nearfuel.itemList.contracts

import com.ktu.components.objects.GasStation
import dagger.Provides

interface ItemListContract {

    interface View{
        fun updateList(list: List<GasStation>)
    }

    interface Presenter{
        fun loadListData()
        fun onDetach()
    }
}