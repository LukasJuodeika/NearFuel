package com.ktu.nearfuel.views.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.ktu.components.contracts.MapContract
import com.ktu.components.presenters.MapPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.map_fragment.view.*
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.ktu.components.objects.GasStation
import com.ktu.nearfuel.ui.main.presenter.MapsNewContract
import com.ktu.nearfuel.ui.main.view.MainMVPView
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import javax.inject.Named
import android.R
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions




class MapFragment : Fragment(), MapContract.View, OnMapReadyCallback
{

    @SuppressLint("MissingPermission")
    override fun onMapReady(mMap: GoogleMap) {
        this.mMap = mMap
        mMap.uiSettings.isMyLocationButtonEnabled = false
        observeMarkersByGoogleLocation()

    }


    private lateinit var presenter: MapContract.Presenter

    @Inject
    @Named("MapFragment")
    internal lateinit var dagger2Presenter: MapsNewContract<MainMVPView>

    private lateinit var navController: NavController
    private lateinit var rootView: View
    private lateinit var mapView: MapView
    private lateinit var mMap: GoogleMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(com.ktu.nearfuel.R.layout.map_fragment, container, false)

        presenter = MapPresenter(this)
        this.rootView = rootView
        setClickListeners(rootView)
        mapView = rootView.findViewById(com.ktu.nearfuel.R.id.map_view) as MapView
        mapView.onCreate(savedInstanceState)


        mapView.getMapAsync(this)

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dagger2Presenter.getStationsNearLocation(latLng = LatLng (54.898521, 23.903597))
    }

    fun observeMarkersByGoogleLocation(){
        val gasStations = Observer<List<GasStation>> { gasStations ->

            for ( station in gasStations)
            {
                mMap.addMarker(
                    MarkerOptions()
                        .position(LatLng(station.lat.toDouble(), station.lng.toDouble()))
                        .title(station.title)
                        .snippet(station.price)
                      //  .icon(BitmapDescriptorFactory.fromResource(R.drawable.btn_plus))
                )
            }
            Log.d("responsegood", gasStations.size.toString())
        }

        dagger2Presenter.getGasStationsLivedata().observe(this, gasStations)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }
    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    private fun setClickListeners(view: View)
    {
        view.add_gas_station.setOnClickListener {
            presenter.addStationClicked()
        }
    }

    private fun performDependencyInjection() = AndroidSupportInjection.inject(this)


    override fun openAddStationFragment() {
        navController.navigate(com.ktu.nearfuel.R.id.action_mapFragment_to_addStationFragment)
    }


    override fun onResume() {
        super.onResume()
        presenter.onResume()
        mapView.onResume()
        navController = rootView.findNavController()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
        mapView.onPause()
    }


    override fun lockDrawer() {
        activity!!.drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    override fun unlockDrawer() {
        activity!!.drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }


}