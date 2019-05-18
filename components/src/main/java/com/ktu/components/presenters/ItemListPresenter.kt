package com.ktu.components.presenters

import com.ktu.components.contracts.ItemListContract
import com.ktu.components.data.GasStationDao
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class ItemListPresenter(
    private val view: ItemListContract.View,
    private val stationsDao: GasStationDao,
    private val backgroundScheduler: Scheduler,
    private val UIScheduler: Scheduler
) : ItemListContract.Presenter {

    private val disposables = CompositeDisposable()

    override fun loadListData() {
        disposables.add(
            stationsDao.getAllGasStations()
                .subscribeOn(backgroundScheduler)
                .observeOn(UIScheduler)
                .subscribe({ view.updateList(it) }, {})
        )
    }

    override fun onDetach() {
        disposables.dispose()
    }
}