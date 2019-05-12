package com.ktu.nearfuel.views.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.ktu.components.contracts.MapContract
import com.ktu.components.presenters.MapPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.map_fragment.view.*
import com.google.android.gms.maps.MapView
import dagger.android.support.AndroidSupportInjection


class MapFragment : Fragment(), MapContract.View, OnMapReadyCallback
{

    @SuppressLint("MissingPermission")
    override fun onMapReady(mMap: GoogleMap) {
        this.mMap = mMap
        mMap.uiSettings.isMyLocationButtonEnabled = false

    }


    private lateinit var presenter: MapContract.Presenter
    private lateinit var navController: NavController
    private lateinit var rootView: View
    private lateinit var mapView: MapView
    private lateinit var mMap: GoogleMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(com.ktu.nearfuel.R.layout.map_fragment, container, false)


        this.rootView = rootView
        mapView = rootView.findViewById(com.ktu.nearfuel.R.id.map_view) as MapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        presenter = MapPresenter(this)
        setClickListeners(rootView)


        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
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