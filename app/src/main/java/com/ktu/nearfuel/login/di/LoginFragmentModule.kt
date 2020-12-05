package com.ktu.nearfuel.login.di

import com.ktu.nearfuel.login.contracts.LoginContract
import com.ktu.nearfuel.login.presenters.LoginPresenter
import com.ktu.nearfuel.network.APIInterface
import com.ktu.nearfuel.network.AuthRepository
import com.ktu.nearfuel.rx.SchedulersFacade
import dagger.Module
import dagger.Provides
import javax.inject.Named


@Module
class LoginFragmentModule {

    @Provides
    internal fun provideItemListPresenter(
        itemListContract: LoginContract.View,
        schedulersFacade: SchedulersFacade,
        @Named("unauthorized") apiInterface: APIInterface,
        authRepository: AuthRepository
    ): LoginContract.Presenter {
        return LoginPresenter(itemListContract, schedulersFacade, authRepository, apiInterface);
    }
}
