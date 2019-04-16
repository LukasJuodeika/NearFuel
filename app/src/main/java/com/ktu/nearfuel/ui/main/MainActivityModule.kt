package com.ktu.nearfuel.ui.main
import com.ktu.nearfuel.ui.main.presenter.MainMVPPresenter
import com.ktu.nearfuel.ui.main.presenter.MainPresenter
import com.ktu.nearfuel.ui.main.view.MainMVPView
import dagger.Module
import dagger.Provides


@Module
class MainActivityModule {

    @Provides
    internal fun provideMainPresenter(mainPresenter: MainPresenter<MainMVPView>)
            : MainMVPPresenter<MainMVPView> = mainPresenter

}