package com.ktu.nearfuel.ui.main

import com.ktu.nearfuel.ui.main.presenter.MapsNewContract
import com.ktu.nearfuel.ui.main.presenter.MapsNewPresenter
import com.ktu.nearfuel.ui.main.view.MainMVPView
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