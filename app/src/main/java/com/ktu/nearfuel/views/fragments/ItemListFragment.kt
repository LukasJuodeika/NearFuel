package com.ktu.nearfuel.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ktu.components.contracts.ItemListContract
import com.ktu.components.presenters.ItemListPresenter
import com.ktu.nearfuel.R

class ItemListFragment : Fragment(), ItemListContract.View
{
    private lateinit var presenter: ItemListContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.item_list_fragment,null)

        presenter = ItemListPresenter(this)

        return rootView
    }
}
