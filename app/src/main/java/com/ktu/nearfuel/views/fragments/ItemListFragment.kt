package com.ktu.nearfuel.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ktu.components.contracts.ItemListContract
import com.ktu.components.objects.GasStation
import com.ktu.components.presenters.ItemListPresenter
import com.ktu.nearfuel.R
import com.ktu.nearfuel.views.components.ListAdapter
import kotlinx.android.synthetic.main.item_list_fragment.*
import kotlinx.android.synthetic.main.item_list_fragment.view.*

class ItemListFragment : Fragment(), ItemListContract.View {
    private lateinit var presenter: ItemListContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.item_list_fragment, null)

        presenter = ItemListPresenter(this)

        var myList = getMockData()

        rootView.recycler_view.apply {
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = LinearLayoutManager(activity)

            // specify an viewAdapter (see also next example)
            adapter = ListAdapter(myList)
        }

        return rootView
    }

    fun getMockData(): List<GasStation> {
        var list = arrayListOf<GasStation>(
            GasStation("Statoil","1.0","benzas","","",122.2),
            GasStation("Circle","1.2","dyzelis","","",122.2),
            GasStation("K","1.4","salera","","",122.2),
            GasStation("Neste","1.5","benzas","","",122.2),
            GasStation("a","1.6","dujos","","",122.2)
        )
        return list
    }

}
