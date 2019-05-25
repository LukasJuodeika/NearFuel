package com.ktu.nearfuel.itemList.di

import com.ktu.components.data.GasStationDao
import com.ktu.nearfuel.itemList.contracts.ItemListContract
import com.ktu.nearfuel.itemList.presenters.ItemListPresenter
import com.ktu.nearfuel.rx.SchedulersFacade
import dagger.Module
import dagger.Provides
import rx.schedulers.Schedulers
import dagger.Binds




@Module
class ItemListFragmentModule {

    @Provides
    internal fun provideItemListPresenter(itemListContract: ItemListContract.View, schedulersFacade: SchedulersFacade,
                                          dao: GasStationDao)
            : ItemListContract.Presenter{
        return ItemListPresenter(view = itemListContract, schedulersFacade = schedulersFacade, stationsDao = dao);
    }


}