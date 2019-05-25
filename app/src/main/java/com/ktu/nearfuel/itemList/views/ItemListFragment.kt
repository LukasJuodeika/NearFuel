package com.ktu.nearfuel.itemList.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ktu.components.data.FuelType
import com.ktu.nearfuel.itemList.contracts.ItemListContract
import com.ktu.components.data.GasStationDao
import com.ktu.components.objects.GasStation
import com.ktu.nearfuel.R
import com.ktu.nearfuel.components.ListAdapter
import com.ktu.nearfuel.components.NavigationHandler
import com.ktu.nearfuel.itemList.contracts.Filter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.item_list_fragment.view.*
import javax.inject.Inject

class ItemListFragment private constructor() : Fragment(), ItemListContract.View, Filter, ListAdapter.OnListItemClickListener {

    private lateinit var rootView: View

    @Inject
    lateinit var presenter: ItemListContract.Presenter

    private lateinit var fuelType: FuelType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        fuelType = FuelType.valueOf(arguments!!.getString(FUEL_TYPE)!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        rootView = inflater.inflate(R.layout.item_list_fragment, null)
        setUpRecyclerView(listOf())
        presenter.loadListData()
        return rootView
    }

    private fun setUpRecyclerView(list: List<GasStation>) {
        rootView.recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = ListAdapter(list, FuelType.DIESEL, this@ItemListFragment)
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

    override fun sortByPrice() {
        presenter.sortByPrice(fuelType)
    }

    override fun filterUnknown() {
        presenter.filterUnknown(fuelType)
    }

    override fun resetList() {
        presenter.loadListData()
    }

    override fun onItemClick(item: GasStation) {

    }

    override fun onItemLongClick(item: GasStation): Boolean {
        val args = Bundle()
        args.putParcelable("amount",item)
        findNavController().navigate(R.id.action_gasStationListFragment_to_addStationFragment, args)
        return true
    }

    override fun onMapClick(item: GasStation) {
        NavigationHandler.openNavigation(context!!, item.lng, item.lat)
    }

    companion object{
        fun newInstance(type: FuelType) : ItemListFragment{
            val args = Bundle()
            args.putString(FUEL_TYPE,type.name)
            val fragment = ItemListFragment()
            fragment.arguments = args
            return fragment
        }

        private const val FUEL_TYPE = "fuelType"
    }
}
