package com.ktu.nearfuel.itemList.di

import com.ktu.nearfuel.itemList.contracts.ItemListContract
import com.ktu.nearfuel.itemList.presenters.ItemListPresenter
import com.ktu.nearfuel.itemList.views.ItemListFragment
import dagger.Binds
import dagger.Module

@Module
abstract class ItemListViewModule {

    @Binds
    internal abstract fun provideItemListview(itemListFragment: ItemListFragment): ItemListContract.View
}