package com.ktu.nearfuel.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.ktu.components.contracts.AddStationContract
import com.ktu.components.objects.GasStation
import com.ktu.components.presenters.AddStationPresenter
import com.ktu.nearfuel.R
import com.ktu.nearfuel.network.Resource
import com.ktu.nearfuel.network.Status
import com.ktu.nearfuel.maps.contracts.MapsNewContract
import com.ktu.nearfuel.maps.views.MainMVPView
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import javax.inject.Named

class AddStationFragment : Fragment(), AddStationContract.View {

    private lateinit var presenter: AddStationContract.Presenter

    lateinit var confirmButton :MaterialButton
    lateinit var gasStationTextInputLayout : TextInputLayout
    lateinit var fuelStationTextInputLayout : TextInputLayout
    lateinit var dieselStationTextInputLayout : TextInputLayout
    lateinit var mNavigation : NavController

    var updateStationObsever = Observer<Resource<GasStation>>
    {
       if(it.status==Status.LOADING)
       {

       }
        else if(it.status == Status.SUCCESS)
        {
           mNavigation.navigate(R.id.action_addStationFragment_to_mapFragment)
           Toast.makeText(activity!!, "Updated successfully", Toast.LENGTH_LONG).show();
        }
    }

    @Inject
    @Named("MapFragment")
    internal lateinit var dagger2Presenter: MapsNewContract<MainMVPView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        mNavigation = findNavController()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.add_gas_station_constraint, container, false)

        presenter = AddStationPresenter(this)
        confirmButton = rootView.findViewById(R.id.confirm_edit)
        gasStationTextInputLayout = rootView.findViewById(R.id.gas_layout)
        fuelStationTextInputLayout = rootView.findViewById(R.id.fuel_layout)
        dieselStationTextInputLayout = rootView.findViewById(R.id.diasel_layout)

        dagger2Presenter.getGasStationUpdateResult().observe(this, updateStationObsever)
        var station = arguments!!.getParcelable<GasStation>("amount")

        confirmButton.setOnClickListener {

            station.fuel_price = fuelStationTextInputLayout.editText!!.text.toString()
            station.gas_price = gasStationTextInputLayout.editText!!.text.toString()
            station.diesel_price = dieselStationTextInputLayout.editText!!.text.toString()
            dagger2Presenter.updateGasStation(station)

            Log.d("response", arguments!!.getParcelable<GasStation>("amount").toString());

        }

        return rootView
    }
}