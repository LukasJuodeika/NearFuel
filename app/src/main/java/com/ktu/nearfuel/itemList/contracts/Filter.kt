package com.ktu.nearfuel.itemList.contracts

interface Filter {
    fun sortByPrice()
    fun filterUnknown()
    fun resetList()
}