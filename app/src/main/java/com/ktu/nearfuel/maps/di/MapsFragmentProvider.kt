package com.ktu.nearfuel.maps.di

import com.ktu.nearfuel.views.fragments.AddStationFragment
import com.ktu.nearfuel.maps.views.MapFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MapsFragmentProvider{

    @ContributesAndroidInjector(modules = [MapsFragmentModule::class])
    internal abstract fun provideMapsFragment() : MapFragment

    @ContributesAndroidInjector(modules = [MapsFragmentModule::class])
    internal abstract fun provideMapsFragment1() : AddStationFragment
}