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
import com.ktu.components.contracts.EditStationContract
import com.ktu.components.objects.GasStation
import com.ktu.components.presenters.EditStationPresenter
import com.ktu.nearfuel.R
import com.ktu.nearfuel.maps.contracts.MapsNewContract
import com.ktu.nearfuel.maps.views.MainMVPView
import com.ktu.nearfuel.network.Resource
import com.ktu.nearfuel.network.Status
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.edit_station_prices.*
import kotlinx.android.synthetic.main.edit_station_prices.view.*
import javax.inject.Inject
import javax.inject.Named

class EditStationFragment : Fragment(), EditStationContract.View {

    private lateinit var presenter: EditStationContract.Presenter

    lateinit var confirmButton: MaterialButton
    lateinit var gasStationTextInputLayout: TextInputLayout
    lateinit var fuelStationTextInputLayout: TextInputLayout
    lateinit var dieselStationTextInputLayout: TextInputLayout
    lateinit var mNavigation: NavController

    var updateStationObsever = Observer<Resource<GasStation>>
    {
        if (it.status == Status.LOADING) {

        } else if (it.status == Status.SUCCESS) {
            mNavigation.navigateUp()
            Toast.makeText(activity!!, "Updated successfully", Toast.LENGTH_LONG).show();
        }
    }

    @Inject
    @Named("MapFragment")
    internal lateinit var dagger2Presenter: MapsNewContract<MainMVPView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mNavigation = findNavController()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.edit_station_prices, container, false)
        val station = arguments!!.getParcelable<GasStation>("amount")
        loadDataToView(rootView, station)

        presenter = EditStationPresenter(this)
        confirmButton = rootView.findViewById(R.id.confirm_edit)
        gasStationTextInputLayout = rootView.findViewById(R.id.gas_layout)
        fuelStationTextInputLayout = rootView.findViewById(R.id.fuel_layout)
        dieselStationTextInputLayout = rootView.findViewById(R.id.diasel_layout)

        dagger2Presenter.getGasStationUpdateResult().observe(this, updateStationObsever)

        confirmButton.setOnClickListener {

            if(station != null && validate(rootView)){
                if(!(chip_diesel.isChecked || chip_gas.isChecked || chip_petrol.isChecked)) {
                    var errorMessage = ""
                    errorMessage ="Select at least one chip to update prices"
                    if (errorMessage.isNotBlank()) {
                        rootView.edit_gas.error = errorMessage
                    }
                    return@setOnClickListener
                }
                if(chip_petrol.isChecked) station.fuel_price = edit_petrol.text.toString()
                if(chip_gas.isChecked) station.gas_price = edit_gas.text.toString()
                if(chip_diesel.isChecked) station.diesel_price = edit_diesel.text.toString()
                    dagger2Presenter.updateGasStation(station)
            }
            Log.d("response", arguments!!.getParcelable<GasStation>("amount").toString())

        }

        return rootView
    }

    private fun loadDataToView(view: View, station: GasStation?): Boolean {
        if (station == null) {
            return false
        }
        view.ti_title.editText!!.setText(station.title)
        view.ti_address.editText!!.setText(station.address)
        view.fuel_layout.editText!!.setText(station.fuel_price)
        view.diasel_layout.editText!!.setText(station.diesel_price)
        view.gas_layout.editText!!.setText(station.gas_price)
        return true
    }

    private fun validate(view : View) :Boolean{

        val petrol = view.edit_petrol.text.toString()
        val diesel = view.edit_diesel.text.toString()
        val gas = view.edit_gas.text.toString()

        val chipPetrol = view.chip_petrol
        val chipDiesel = view.chip_diesel
        val chipGas = view.chip_gas

        var isValid = true
        var errorMessage = ""

        if(chipDiesel.isChecked){
            errorMessage = validatePrice(diesel)
            if (errorMessage.isNotBlank()) {
                view.edit_diesel.error = errorMessage
                isValid = false
            }
        }else{
            view.edit_diesel.error = null
        }

        if(chipPetrol.isChecked){
            errorMessage = validatePrice(petrol)
            if (errorMessage.isNotBlank()) {
                view.edit_petrol.error = errorMessage
                isValid = false
            }
        }else{
            view.edit_petrol.error = null
        }

        if(chipGas.isChecked){
            errorMessage = validatePrice(gas)
            if (errorMessage.isNotBlank()) {
                view.edit_gas.error = errorMessage
                isValid = false
            }
        }else{
            view.edit_gas.error = null
        }

        return isValid
    }

    private fun validatePrice(price : String) : String{
        if(price.isBlank()){
            return getString(R.string.empty_error)
        }
        if(price.toDouble() > 5 || price.toDouble() < 0.01){
            return getString(R.string.bad_price_error)
        }
        return ""
    }
}