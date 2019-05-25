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
import com.ktu.nearfuel.network.Resource
import io.reactivex.Completable
import okhttp3.ResponseBody


class MapsNewPresenter<V : MainMVPView> @Inject constructor(
    protected val disposable: CompositeDisposable,
    protected val apiInterface: APIInterface,
    protected val gasStationDao: GasStationDao


) : MapsNewContract<V> {
    override fun getGasStationUpdateResult(): MutableLiveData<Resource<GasStation>> {
       return updategasStationLivedata
    }

    val updategasStationLivedata =  MutableLiveData<Resource<GasStation>>()
    override fun updateGasStation(gasStation: GasStation) {
        gasStation.fuel_price = "shshhshshshs"
        updategasStationLivedata.value = Resource.loading(null)
        disposable.add(apiInterface.updateStation(
            gasStation
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Completable.fromAction {
                    gasStationDao.updateGasStation(gasStation)
                }
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
        getStationsFromAPI(latLng)
       // getStationsFromMapsAPI(latLng)

    }


    fun getStationsFromAPI(latLng: LatLng){
        val location = latLng.latitude.toString() + "," + latLng.longitude.toString()
        disposable.add(apiInterface.getAllGasStations(
            location
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
                        Log.d("responseerror", it.message)

                        })

            },
                {
                    Log.d("responseerror", it.message)

                }, {

                }
            ))
    }

    fun startObservingStationsFromDao() {
        gasStationDao.getAllGasStations()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
               // Log.d("responseerror", it.size.toString())
                listGasStationLiveData.value = it

            })
    }

    val listGasStationLiveData: MutableLiveData<List<GasStation>> by lazy {
        MutableLiveData<List<GasStation>>()
    }


}