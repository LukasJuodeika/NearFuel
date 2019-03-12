package com.ktu.nearfuel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class AddStationFragment : Fragment() {

    private var mTitle : EditText? = null
    private var mAddress : EditText? = null
    private var mPetrolPrice : EditText? = null
    private var mDieselPrice : EditText? = null
    private var mGasPrice : EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.add_gas_station, container, false)

        mTitle = view.findViewById(R.id.et_station_title)
        mAddress = view.findViewById(R.id.et_station_address)
        mPetrolPrice = view.findViewById(R.id.et_petrol_price)
        mDieselPrice = view.findViewById(R.id.et_diesel_price)
        mGasPrice = view.findViewById(R.id.et_gas_price)

        return view
    }
}