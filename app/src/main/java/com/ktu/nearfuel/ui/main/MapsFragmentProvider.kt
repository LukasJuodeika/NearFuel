package com.ktu.nearfuel.ui.main

import com.ktu.nearfuel.views.fragments.MapFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MapsFragmentProvider{

    @ContributesAndroidInjector(modules = [MapsFragmentModule::class])
    internal abstract fun provideMapsFragment() : MapFragment
}