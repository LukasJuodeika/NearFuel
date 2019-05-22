package com.ktu.nearfuel.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.ktu.components.contracts.AddStationContract
import com.ktu.components.presenters.AddStationPresenter
import com.ktu.nearfuel.R

class AddStationFragment : Fragment(), AddStationContract.View {

    private lateinit var presenter: AddStationContract.Presenter

    lateinit var confirmButton :MaterialButton
    lateinit var gasStationTextInputLayout : TextInputLayout
    lateinit var fuelStationTextInputLayout : TextInputLayout
    lateinit var dieselStationTextInputLayout : TextInputLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.add_gas_station_constraint, container, false)

        presenter = AddStationPresenter(this)
        confirmButton = rootView.findViewById(R.id.confirm_edit)
        gasStationTextInputLayout = rootView.findViewById(R.id.gas_layout)
        fuelStationTextInputLayout = rootView.findViewById(R.id.fuel_layout)

        return rootView
    }
}