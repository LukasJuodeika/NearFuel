package com.ktu.nearfuel.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ktu.components.contracts.ItemListContract
import com.ktu.components.data.GasStationDao
import com.ktu.components.objects.GasStation
import com.ktu.components.presenters.ItemListPresenter
import com.ktu.nearfuel.R
import com.ktu.nearfuel.components.ListAdapter
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.item_list_fragment.view.*
import javax.inject.Inject

class ItemListFragment : Fragment(), ItemListContract.View {

    private lateinit var presenter: ItemListContract.Presenter
    @Inject
    lateinit var stationsDao: GasStationDao
    private lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        rootView = inflater.inflate(R.layout.item_list_fragment, null)
        presenter = ItemListPresenter(this, stationsDao, Schedulers.io(), AndroidSchedulers.mainThread())
        setUpRecyclerView(listOf())
        return rootView
    }

    private fun setUpRecyclerView(list: List<GasStation>) {
        rootView.recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = ListAdapter(list)
        }
    }

    override fun updateList(list: List<GasStation>) {
        rootView.recycler_view.apply {
            (adapter as ListAdapter).updateList(list)
        }
    }

    override fun onDetach() {
        presenter.onDetach()
        super.onDetach()
    }
}
