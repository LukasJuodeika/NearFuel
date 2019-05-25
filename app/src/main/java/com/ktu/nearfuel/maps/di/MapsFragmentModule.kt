package com.ktu.nearfuel.maps.di

import com.ktu.nearfuel.maps.contracts.MapsNewContract
import com.ktu.nearfuel.maps.presenters.MapsNewPresenter
import com.ktu.nearfuel.maps.views.MainMVPView
import dagger.Module
import dagger.Provides
import javax.inject.Named


@Module
class MapsFragmentModule {

    @Provides
    @Named("MapFragment")
    internal fun provideMapsPresenter(mapsNewPresenter: MapsNewPresenter<MainMVPView>)
            : MapsNewContract<MainMVPView> = mapsNewPresenter
}