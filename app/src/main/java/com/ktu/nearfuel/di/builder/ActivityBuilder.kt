package com.ktu.nearfuel.di.builder

import com.ktu.nearfuel.ui.main.MainActivityModule
import com.ktu.nearfuel.ui.main.MapsFragmentProvider
import com.ktu.nearfuel.views.activities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(MainActivityModule::class),
        (MapsFragmentProvider::class)])
    abstract fun bindMainActivityActivity(): MainActivity



}