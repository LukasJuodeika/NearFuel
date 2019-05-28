package com.ktu.nearfuel.login.di

import com.ktu.nearfuel.itemList.di.ItemListFragmentModule
import com.ktu.nearfuel.itemList.di.ItemListViewModule
import com.ktu.nearfuel.itemList.views.ItemListFragment
import com.ktu.nearfuel.login.views.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class LoginFragmentProvider{

    @ContributesAndroidInjector(modules = [LoginFragmentViewModule::class, LoginFragmentModule::class])
    internal abstract fun provideItemFragment() : LoginFragment
}