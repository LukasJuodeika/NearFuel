package com.ktu.nearfuel.ui.main.presenter

import com.google.android.gms.maps.model.LatLng
import com.ktu.nearfuel.di.module.RetrofitModule
import com.ktu.nearfuel.network.APIInterface
import com.ktu.nearfuel.ui.main.view.MainMVPView

import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject


class MapsNewPresenter<V : MainMVPView> @Inject constructor(protected val disposable: CompositeDisposable, protected val apiInterface:APIInterface) : MapsNewContract<V> {
    override fun getStationsNearLocation(latLng: LatLng) {
        apiInterface.getDocumentsTypes("tests")


    }




}