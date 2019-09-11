package com.ktu.nearfuel.views.activities

import com.ktu.nearfuel.maps.contracts.MapsNewContract
import com.ktu.nearfuel.maps.presenters.MapsNewPresenter
import com.ktu.nearfuel.maps.views.MainMVPView
import dagger.Module
import dagger.Provides


@Module
class MainActivityModule {

    @Provides
    internal fun provideMainPresenter(mapsNewPresenter: MapsNewPresenter<MainMVPView>)
            : MapsNewContract<MainMVPView> = mapsNewPresenter

}