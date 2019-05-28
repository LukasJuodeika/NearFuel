package com.ktu.nearfuel.login.di

import com.ktu.nearfuel.login.contracts.LoginContract
import com.ktu.nearfuel.login.views.LoginFragment
import dagger.Binds
import dagger.Module

@Module
abstract class LoginFragmentViewModule {

    @Binds
    internal abstract fun provideItemListview(LoginFragment: LoginFragment): LoginContract.View
}