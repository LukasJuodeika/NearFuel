package com.ktu.nearfuel.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ktu.components.contracts.SignUpContract
import com.ktu.components.presenters.SignUpPresenter
import com.ktu.nearfuel.R

class SignUpFragment : Fragment(), SignUpContract.View {

    private lateinit var mPresenter : SignUpPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_signup, container, false)
        mPresenter = SignUpPresenter(this)
        return view
    }
}