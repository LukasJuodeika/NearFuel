package com.ktu.nearfuel.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.ktu.nearfuel.R
import kotlinx.android.synthetic.main.map_fragment.view.*

class MapFragment : Fragment()
{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.map_fragment, container, false)

        setClickListeners(rootView)
        return rootView
    }

    private fun setClickListeners(view: View)
    {
        view.add_gas_station.setOnClickListener {
            view.findNavController().navigate(R.id.action_mapFragment_to_addStationFragment)
        }
    }
}