package com.ktu.nearfuel.maps.presenters

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.ktu.components.data.GasStationDao
import com.ktu.components.objects.GasStation
import com.ktu.nearfuel.maps.contracts.MapsNewContract
import com.ktu.nearfuel.maps.views.MainMVPView
import com.ktu.nearfuel.network.APIInterface
import com.ktu.nearfuel.network.Resource
import com.ktu.nearfuel.network.models.GasStationRequestBody
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
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
        disposable.add(apiInterface.updateStation(
            GasStationRequestBody(
                gasStation.address,
                gasStation.created_at,
                gasStation.diesel_price,
                gasStation.fuelType,
                gasStation.fuel_price,
                gasStation.gas_price,
                gasStation.lat,
                gasStation.lng,
                gasStation.id,
                gasStation.title,
                gasStation.created_at,
                gasStation.user_id
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Completable.fromAction {
                    gasStationDao.insertGasStation(it)
                }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    }, {
                        Log.d("responseerror", it.message)

                    })
                updategasStationLivedata.value = Resource.success(it)
                Log.d("responseerror", "updated")

            },
                {
                    updategasStationLivedata.value = Resource.error("error", null)
                    Log.d("responseerror", it.message)

                }, {

                }
            ))
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
                                        station.gas_price = it.first().gasPrice
                                        station.fuel_price = it.first().fuelPrice
                                        station.diesel_price = it.first().dieselPrice
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