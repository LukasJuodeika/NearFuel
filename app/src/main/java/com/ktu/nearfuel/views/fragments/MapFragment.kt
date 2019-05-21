package com.ktu.nearfuel.views.fragments

import android.annotation.SuppressLint
import android.location.Location
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
import com.google.android.gms.location.*
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
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.ktu.nearfuel.R
import com.ktu.nearfuel.views.StationInfoWindowAdapter


class MapFragment : Fragment(), MapContract.View, OnMapReadyCallback
{

    private lateinit var presenter: MapContract.Presenter

    @Inject
    @Named("MapFragment")
    internal lateinit var dagger2Presenter: MapsNewContract<MainMVPView>

    private lateinit var navController: NavController
    private lateinit var rootView: View
    private lateinit var mapView: MapView
    private lateinit var mMap: GoogleMap
    //Location
    private lateinit var mRequest: LocationRequest
    private lateinit var mFusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var mLocationCallback: LocationCallback
    //For following user location
    private var mOutOfFocus : Boolean = false
    private lateinit var mLastLocation : Location

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.map_fragment, container, false)

        this.rootView = rootView
        mapView = rootView.findViewById(R.id.map_view) as MapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        presenter = MapPresenter(this)
        setClickListeners(rootView)

        return rootView
    }
    override fun onDestroyView() {
        super.onDestroyView()
        mFusedLocationProviderClient.removeLocationUpdates(mLocationCallback)
        mapView.onDestroy()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
        mapView.onResume()
        navController = rootView.findNavController()
        mOutOfFocus = false
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
        mapView.onPause()
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dagger2Presenter.getStationsNearLocation(latLng = LatLng (54.898521, 23.903597))
    }

    fun observeMarkersByGoogleLocation(){
        val gasStations = Observer<List<GasStation>> { gasStations ->

            for ( station in gasStations)
            {
                val marker = mMap.addMarker(
                    MarkerOptions()
                        .position(LatLng(station.lat.toDouble(), station.lng.toDouble()))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.station_marker))
                )
                marker.tag = station
            }
            Log.d("responsegood", gasStations.size.toString())
        }

        dagger2Presenter.getGasStationsLivedata().observe(this, gasStations)
    }


    private fun setClickListeners(view: View)
    {
        view.add_gas_station.setOnClickListener {
            presenter.addStationClicked()
        }
    }

    private fun performDependencyInjection() = AndroidSupportInjection.inject(this)


    override fun openAddStationFragment() {
        navController.navigate(R.id.action_mapFragment_to_addStationFragment)
    }


    override fun onMapReady(mMap: GoogleMap) {
        this.mMap = mMap
        setMapSettings()
        setUpLocation()
        observeMarkersByGoogleLocation()

    }
    @SuppressLint("MissingPermission")
    private fun setMapSettings(){
        mMap.uiSettings.isMyLocationButtonEnabled = true
        mMap.isMyLocationEnabled = true
        mMap.setOnCameraMoveListener { mOutOfFocus = true }
        mMap.setOnMyLocationButtonClickListener {
            mOutOfFocus = false
            moveCamera(mLastLocation, true)
            true }
        mMap.setInfoWindowAdapter(StationInfoWindowAdapter(context!!.applicationContext))
        mMap.setOnInfoWindowLongClickListener {
            openAddStationFragment()
        }
    }

    @SuppressLint("MissingPermission")
    private fun setUpLocation() {
        mLocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                if (locationResult == null) {
                    return
                }
                mLastLocation = locationResult.lastLocation
                if(!mOutOfFocus) moveCamera(mLastLocation, false)
            }
        }
        mRequest = LocationRequest.create()
        mRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mRequest.interval = LOCATION_INTERVAL
        mRequest.fastestInterval = LOCATION_INTERVAL
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity!!)
        mFusedLocationProviderClient.requestLocationUpdates(mRequest, mLocationCallback, null)
    }

    private fun moveCamera(location: Location, animate: Boolean){
        val cameraPosition = CameraPosition.Builder()
            .target(LatLng(location.latitude, location.longitude)
            ).zoom(LOCATION_ZOOM).build()

        val cameraUpdate = CameraUpdateFactory
            .newCameraPosition(cameraPosition)
        if(animate){
            mMap.animateCamera(cameraUpdate)
        }else{
            mMap.moveCamera(cameraUpdate)
        }
    }


    override fun lockDrawer() {
        activity!!.drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    override fun unlockDrawer() {
        activity!!.drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    companion object{
        private const val LOCATION_INTERVAL = 10_000L
        private const val LOCATION_ZOOM = 16f
    }

}