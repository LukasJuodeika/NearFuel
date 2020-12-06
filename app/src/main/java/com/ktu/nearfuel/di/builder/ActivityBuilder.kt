package com.ktu.nearfuel.di.builder

import com.ktu.nearfuel.itemList.di.ItemListFragmentProvider
import com.ktu.nearfuel.login.di.LoginFragmentProvider
import com.ktu.nearfuel.login.di.SignUpFragmentProvider
import com.ktu.nearfuel.login.views.AuthenticationActivity
import com.ktu.nearfuel.views.activities.MainActivityModule
import com.ktu.nearfuel.maps.di.MapsFragmentProvider
import com.ktu.nearfuel.views.activities.MainActivity
import com.ktu.nearfuel.views.fragments.SignUpFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(
        modules = [(MainActivityModule::class),
            (MapsFragmentProvider::class), (ItemListFragmentProvider::class)]
    )
    abstract fun bindMainActivityActivity(): MainActivity

    @ContributesAndroidInjector(modules = [(LoginFragmentProvider::class), (SignUpFragmentProvider::class)])
    abstract fun bindAuthenticationActivityActivity(): AuthenticationActivity


}