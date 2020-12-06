package com.ktu.nearfuel.login.di

import com.ktu.nearfuel.itemList.di.ItemListFragmentModule
import com.ktu.nearfuel.itemList.di.ItemListViewModule
import com.ktu.nearfuel.itemList.views.ItemListFragment
import com.ktu.nearfuel.login.views.LoginFragment
import com.ktu.nearfuel.views.fragments.SignUpFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class SignUpFragmentProvider {

    @ContributesAndroidInjector
    internal abstract fun provideItemFragment(): SignUpFragment
}