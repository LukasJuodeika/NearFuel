package com.ktu.nearfuel.itemList.presenters

import com.ktu.components.data.FuelType
import com.ktu.nearfuel.itemList.contracts.ItemListContract
import com.ktu.components.data.GasStationDao
import com.ktu.components.objects.GasStation
import com.ktu.nearfuel.rx.SchedulersFacade
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ItemListPresenter
@Inject constructor(
    private val view: ItemListContract.View,
    private val schedulersFacade: SchedulersFacade,
    var stationsDao: GasStationDao
) : ItemListContract.Presenter {


    private val disposables = CompositeDisposable()
    private lateinit var dataList: ArrayList<GasStation>

    override fun loadListData() {
        disposables.add(
            stationsDao.getAllGasStations()
                .subscribeOn(schedulersFacade.io())
                .observeOn(schedulersFacade.ui())
                .subscribe({
                    view.updateList(it)
                    dataList = it as ArrayList<GasStation>
                }, {})
        )
    }

    override fun onDetach() {
        disposables.dispose()
    }

    override fun sortByPrice(fuelType: FuelType) {
        when (fuelType) {
            FuelType.PETROL -> dataList.sortBy { it.fuel_price?.toDoubleOrNull() }
            FuelType.DIESEL -> dataList.sortBy { it.diesel_price?.toDoubleOrNull() }
            FuelType.GAS -> dataList.sortBy { it.gas_price?.toDoubleOrNull() }
        }
        view.updateList(dataList)
    }

    override fun filterUnknown(fuelType: FuelType) {
        dataList = when (fuelType) {
            FuelType.PETROL -> dataList.filter { it.fuel_price != null } as ArrayList<GasStation>
            FuelType.DIESEL -> dataList.filter { it.diesel_price != null } as ArrayList<GasStation>
            FuelType.GAS -> dataList.filter { it.gas_price != null } as ArrayList<GasStation>
        }
        view.updateList(dataList)
    }
}