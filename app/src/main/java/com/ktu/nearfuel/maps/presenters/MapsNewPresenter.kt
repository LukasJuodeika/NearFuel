package com.ktu.nearfuel.maps.presenters

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.ktu.components.data.GasStationDao
import com.ktu.components.objects.GasStation
import com.ktu.nearfuel.maps.contracts.MapsNewContract
import com.ktu.nearfuel.maps.views.MainMVPView
import com.ktu.nearfuel.network.APIInterface
import com.ktu.nearfuel.network.Price
import com.ktu.nearfuel.network.Resource
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.time.LocalDateTime
import javax.inject.Inject


class MapsNewPresenter<V : MainMVPView> @Inject constructor(
    protected val disposable: CompositeDisposable,
    protected val apiInterface: APIInterface,
    protected val gasStationDao: GasStationDao
) : MapsNewContract<V> {
    override fun getGasStationUpdateResult(): MutableLiveData<Resource<GasStation>> {
        return updategasStationLivedata
    }

    val updategasStationLivedata = MutableLiveData<Resource<GasStation>>()

    override fun updateGasStation(gasStation: GasStation) {
        updategasStationLivedata.value = Resource.loading(null)
        disposable.add(
            addOrUpdateStation(gasStation)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    updategasStationLivedata.value = Resource.success(gasStation)
                    Log.d("responseerror", "updated")
                },
                    {
                        updategasStationLivedata.value = Resource.error("error", null)
                        Log.d("responseerror", it.message)

                    }
                ))
    }


    private fun addOrUpdateStation(station: GasStation): Completable {
        return if(station.id > 0) {
            updateStation(station)
        } else {
            addStation(station)
        }
    }

    @SuppressLint("NewApi")
    private fun updateStation(gasStation: GasStation): Completable {
        return apiInterface.updateStation(gasStation.id, gasStation)
            .andThen {
                apiInterface.addPrice(
                    gasStation.id,
                    Price(
                        dateTime = LocalDateTime.now().toString(),
                        gasPrice = gasStation.gas_price ?: "",
                        fuelPrice = gasStation.fuel_price ?: "",
                        dieselPrice = gasStation.diesel_price ?: ""
                    )
                ).subscribe(it)
            }
            .doOnComplete { gasStationDao.insertGasStation(gasStation) }
    }

    @SuppressLint("NewApi")
    private fun addStation(gasStation: GasStation): Completable {
        return apiInterface.addStation(gasStation)
            .flatMapCompletable {station ->
                apiInterface.addPrice(
                    station.id,
                    Price(
                        dateTime = LocalDateTime.now().toString(),
                        gasPrice = gasStation.gas_price ?: "",
                        fuelPrice = gasStation.fuel_price ?: "",
                        dieselPrice = gasStation.diesel_price ?: ""
                    )
                ).doOnComplete { gasStationDao.insertGasStation(station) }
            }
    }

    override fun getGasStationsLivedata(): MutableLiveData<List<GasStation>> {
        return listGasStationLiveData
    }

    override fun getStationsNearLocation(latLng: LatLng) {
        startObservingStationsFromDao()
        getStationsFromAPI()
    }


    fun getStationsFromAPI() {
        disposable.add(
            apiInterface.getAllGasStations()
                .flatMapCompletable {
                    Completable.concat(
                        it.map { station ->
                            apiInterface.getPrices(station.id)
                                .doOnSuccess { it ->
                                    if (it.isNotEmpty()) {
                                        station.gas_price = it.last().gasPrice
                                        station.fuel_price = it.last().fuelPrice
                                        station.diesel_price = it.last().dieselPrice
                                    }
                                    gasStationDao.insertGasStation(station)
                                }.ignoreElement()
                        }
                    )
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                }, {
                    it.printStackTrace()
                })
        )

    }

    fun startObservingStationsFromDao() {
        disposable.add(gasStationDao.getAllGasStations()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                listGasStationLiveData.value = it

            })
    }

    val listGasStationLiveData: MutableLiveData<List<GasStation>> by lazy {
        MutableLiveData<List<GasStation>>()
    }


}