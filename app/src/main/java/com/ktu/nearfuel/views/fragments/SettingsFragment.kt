package com.ktu.nearfuel.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ktu.components.contracts.SettingsContract
import com.ktu.components.presenters.SettingsPresenter
import com.ktu.nearfuel.R

class SettingsFragment : Fragment(), SettingsContract.View
{
    private lateinit var presenter: SettingsContract.Presenter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.settings_fragment,null)
        presenter = SettingsPresenter(this)
        return rootView
    }
}