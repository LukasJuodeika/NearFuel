package com.ktu.nearfuel.ui.main.presenter

import android.util.Log
import com.google.android.gms.maps.model.LatLng
import com.ktu.components.data.GasStationDao
import com.ktu.components.objects.GasStation
import com.ktu.components.objects.NearestGasStation.NearestGasStationsJsonResponse
import com.ktu.nearfuel.di.module.RetrofitModule
import com.ktu.nearfuel.network.APIInterface
import com.ktu.nearfuel.ui.main.view.MainMVPView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import io.reactivex.Observable
import kotlinx.coroutines.Dispatchers
import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import io.reactivex.Completable
import io.reactivex.functions.Consumer
import io.reactivex.internal.util.NotificationLite.accept


class MapsNewPresenter<V : MainMVPView> @Inject constructor(
    protected val disposable: CompositeDisposable,
    protected val apiInterface: APIInterface,
    protected val gasStationDao:GasStationDao
) : MapsNewContract<V> {
    override fun getStationsNearLocation(latLng: LatLng) {
        val location= latLng.latitude.toString() + "," +latLng.longitude.toString()
        disposable.add(apiInterface.getNearestGasStations(location, "distance", "gas_station","AIzaSyCSXmOpvyXwUwosS07ROsqv0FLICbIYTBo")
            .subscribeOn(Schedulers.io())

            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val gasStationsList:ArrayList<GasStation> = arrayListOf()
                for (e in it.results) {
                    val gasStation = GasStation()
                    gasStation.title = e.name
                    gasStation.lat = e.geometry.location.lat.toString()
                    gasStation.lng = e.geometry.location.lng.toString()
                    gasStationsList.add(gasStation)
                    Completable.fromAction({ gasStationDao.insertGasStation(gasStation) }).subscribeOn(Schedulers.io()).
                        observeOn(AndroidSchedulers.mainThread()).
                        subscribe({
                    }, {

                    })
                    //gasStationDao.insertGasStation(gasStation)
                }

                Log.d("response", it.toString())
            },
                {

            }, {

                }
            ))
        getAllGasStations()
                }

    fun getAllGasStations()
            {
                gasStationDao.getAllGasStations()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.d("response", it.size.toString())

                    // showListOfEmployess(employees);
            })




    }




    }