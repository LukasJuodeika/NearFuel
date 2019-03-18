package com.ktu.nearfuel.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.ktu.components.contracts.MapContract
import com.ktu.components.presenters.MapPresenter
import com.ktu.nearfuel.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.map_fragment.view.*

class MapFragment : Fragment(), MapContract.View
{

    private lateinit var presenter: MapContract.Presenter
    private lateinit var navController: NavController
    private lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.map_fragment, container, false)

        presenter = MapPresenter(this)
        this.rootView = rootView
        setClickListeners(rootView)

        return rootView
    }

    private fun setClickListeners(view: View)
    {
        view.add_gas_station.setOnClickListener {
            presenter.addStationClicked()
        }
    }

    override fun openAddStationFragment() {
        navController.navigate(R.id.action_mapFragment_to_addStationFragment)
    }


    override fun onResume() {
        super.onResume()
        presenter.onResume()
        navController = rootView.findNavController()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }


    override fun lockDrawer() {
        activity!!.drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    override fun unlockDrawer() {
        activity!!.drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }


}