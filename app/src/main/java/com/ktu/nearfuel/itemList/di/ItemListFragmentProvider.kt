package com.ktu.nearfuel.itemList.di

import com.ktu.nearfuel.itemList.views.ItemListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ItemListFragmentProvider{

    @ContributesAndroidInjector(modules = [ItemListFragmentModule::class, ItemListViewModule::class])
    internal abstract fun provideItemFragment() : ItemListFragment
}