package com.ktu.nearfuel.ui.main
import com.ktu.nearfuel.ui.main.presenter.MapsNewContract
import com.ktu.nearfuel.ui.main.presenter.MapsNewPresenter
import com.ktu.nearfuel.ui.main.view.MainMVPView
import dagger.Module
import dagger.Provides


@Module
class MainActivityModule {

    @Provides
    internal fun provideMainPresenter(mapsNewPresenter: MapsNewPresenter<MainMVPView>)
            : MapsNewContract<MainMVPView> = mapsNewPresenter

}