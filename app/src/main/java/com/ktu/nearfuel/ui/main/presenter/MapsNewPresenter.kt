package com.ktu.nearfuel.ui.main.presenter

import android.util.Log
import com.google.android.gms.maps.model.LatLng
import com.ktu.components.data.GasStationDao
import com.ktu.components.objects.GasStation
import com.ktu.nearfuel.network.APIInterface
import com.ktu.nearfuel.ui.main.view.MainMVPView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import androidx.lifecycle.MutableLiveData
import io.reactivex.Completable


class MapsNewPresenter<V : MainMVPView> @Inject constructor(
    protected val disposable: CompositeDisposable,
    protected val apiInterface: APIInterface,
    protected val gasStationDao: GasStationDao
) : MapsNewContract<V> {
    override fun getGasStationsLivedata(): MutableLiveData<List<GasStation>> {
        return listGasStationLiveData
    }

    override fun getStationsNearLocation(latLng: LatLng) {
        startObservingStationsFromDao()
        getStationsFromAPI(latLng)
       // getStationsFromMapsAPI(latLng)

    }
//    fun getStationsFromMapsAPI(latLng: LatLng){
//        val location = latLng.latitude.toString() + "," + latLng.longitude.toString()
//        disposable.add(apiInterface.getNearestGasStations(
//            location,
//            "distance",
//            "gas_station",
//            "AIzaSyCSXmOpvyXwUwosS07ROsqv0FLICbIYTBo"
//        )
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                val gasStationsList: ArrayList<GasStation> = arrayListOf()
//                for (e in it.results) {
//                    val gasStation = GasStation()
//                    gasStation.title = e.name
//                    gasStation.lat = e.geometry.location.lat.toString()
//                    gasStation.lng = e.geometry.location.lng.toString()
//                    gasStationsList.add(gasStation)
//                    Completable.fromAction {
//                        gasStationDao.insertGasStation(gasStation)
//                    }.subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread()).subscribe({
//                        }, {
//
//                        })
//                }
//
//                Log.d("response", it.toString())
//            },
//                {
//
//                }, {
//
//                }
//            ))
//    }
    fun getStationsFromAPI(latLng: LatLng){
        val location = latLng.latitude.toString() + "," + latLng.longitude.toString()
        disposable.add(apiInterface.getAllGasStations(
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val gasStationResponse  = it
                Completable.fromAction {
                    gasStationDao.insertAllGasStations(gasStationResponse.data)
                }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    }, {

                    })
            },
                {

                }, {

                }
            ))
    }

    fun startObservingStationsFromDao() {
        gasStationDao.getAllGasStations()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("response", it.size.toString())
                listGasStationLiveData.value = it

            })
    }

    val listGasStationLiveData: MutableLiveData<List<GasStation>> by lazy {
        MutableLiveData<List<GasStation>>()
    }


}