package com.ktu.nearfuel.itemList.presenters

import com.ktu.nearfuel.itemList.contracts.ItemListContract
import com.ktu.components.data.GasStationDao
import com.ktu.nearfuel.rx.SchedulersFacade
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ItemListPresenter
    @Inject constructor(
        private val view: ItemListContract.View,
        private val schedulersFacade: SchedulersFacade,
        var stationsDao: GasStationDao
)
 : ItemListContract.Presenter {


    private val disposables = CompositeDisposable()

    override fun loadListData() {
        disposables.add(
            stationsDao.getAllGasStations()
                .subscribeOn(schedulersFacade.io())
                .observeOn(schedulersFacade.ui())
                .subscribe({ view.updateList(it) }, {})
        )
    }

    override fun onDetach() {
        disposables.dispose()
    }
}