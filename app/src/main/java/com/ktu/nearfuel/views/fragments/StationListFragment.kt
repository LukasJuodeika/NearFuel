package com.ktu.nearfuel.views.fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ktu.components.contracts.StationListContract
import com.ktu.components.presenters.StationListPresenter
import com.ktu.nearfuel.R
import com.ktu.nearfuel.views.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.gas_station_list_view.view.*

class StationListFragment : Fragment(), StationListContract.View
{
    private lateinit var pagerAdapter: ViewPagerAdapter
    private lateinit var presenter: StationListContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.gas_station_list_view,null)
        setupViewPager(rootView)
        return rootView
    }

    private fun setupViewPager(view: View)
    {
        pagerAdapter = ViewPagerAdapter(childFragmentManager)
        view.viewpager.adapter = pagerAdapter
        view.tablayout.setupWithViewPager(view.viewpager)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter = StationListPresenter(this)
        presenter.onAttach()
    }

    override fun onDetach() {
        super.onDetach()
        presenter.onDetach()
    }

    override fun changeElevation(elevation : Float){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            activity!!.toolbar.elevation = elevation
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.stations_list_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.sort1 ->{
                Toast.makeText(context, "Sort1", Toast.LENGTH_SHORT).show()
                presenter.sort1()
            }
            R.id.sort2 ->{
                Toast.makeText(context, "Sort2", Toast.LENGTH_SHORT).show()
                presenter.sort2()
            }
            R.id.sort3 ->{
                Toast.makeText(context, "Sort3", Toast.LENGTH_SHORT).show()
                presenter.sort3()
            }
            R.id.sort4 ->{
                Toast.makeText(context, "Sort4", Toast.LENGTH_SHORT).show()
                presenter.sort4()
            }
            else ->
                return super.onOptionsItemSelected(item)
        }
        return true
    }
}