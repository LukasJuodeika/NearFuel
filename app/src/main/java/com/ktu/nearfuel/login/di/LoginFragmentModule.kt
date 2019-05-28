package com.ktu.nearfuel.login.di

import com.google.firebase.auth.FirebaseAuth
import com.ktu.components.data.GasStationDao
import com.ktu.nearfuel.di.module.RetrofitModule
import com.ktu.nearfuel.itemList.contracts.ItemListContract
import com.ktu.nearfuel.itemList.presenters.ItemListPresenter
import com.ktu.nearfuel.login.contracts.LoginContract
import com.ktu.nearfuel.login.presenters.LoginPresenter
import com.ktu.nearfuel.network.APIInterface
import com.ktu.nearfuel.rx.SchedulersFacade
import dagger.Module
import dagger.Provides
import rx.schedulers.Schedulers
import dagger.Binds




@Module
class LoginFragmentModule  {

    @Provides
    internal fun provideItemListPresenter(itemListContract: LoginContract.View, schedulersFacade: SchedulersFacade, apiInterface: APIInterface, mAuth: FirebaseAuth)
            : LoginContract.Presenter{
        return LoginPresenter(itemListContract, schedulersFacade, mAuth, apiInterface);
    }


}