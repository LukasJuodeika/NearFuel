package com.ktu.nearfuel.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ktu.components.Mocks
import com.ktu.components.contracts.ItemListContract
import com.ktu.components.objects.GasStation
import com.ktu.components.presenters.ItemListPresenter
import com.ktu.nearfuel.R
import com.ktu.nearfuel.views.components.ListAdapter
import kotlinx.android.synthetic.main.item_list_fragment.view.*

class ItemListFragment : Fragment(), ItemListContract.View {
    private lateinit var presenter: ItemListContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.item_list_fragment, null)
        presenter = ItemListPresenter(this)
        setUpRecyclerView(rootView, Mocks.getGasStations())
        return rootView
    }

    private fun setUpRecyclerView(rootView: View, list: List<GasStation>) {
        rootView.recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = ListAdapter(list)
        }
    }

}
