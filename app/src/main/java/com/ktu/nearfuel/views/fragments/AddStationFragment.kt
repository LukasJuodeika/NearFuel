package com.ktu.nearfuel.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ktu.components.contracts.AddStationContract
import com.ktu.components.presenters.AddStationPresenter
import com.ktu.nearfuel.R

class AddStationFragment : Fragment(), AddStationContract.View {

    private lateinit var presenter: AddStationContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.add_gas_station_constraint, container, false)

        presenter = AddStationPresenter(this)

        return rootView
    }
}