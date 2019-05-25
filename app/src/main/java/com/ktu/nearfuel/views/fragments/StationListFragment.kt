package com.ktu.nearfuel.views.fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.ktu.components.contracts.StationListContract
import com.ktu.components.presenters.StationListPresenter
import com.ktu.nearfuel.R
import com.ktu.nearfuel.itemList.contracts.Filter
import com.ktu.nearfuel.itemList.views.ItemListFragment
import com.ktu.nearfuel.views.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.gas_station_list_view.view.*

class StationListFragment : Fragment(), StationListContract.View
{
    private lateinit var pagerAdapter: ViewPagerAdapter
    private lateinit var presenter: StationListContract.Presenter
    private lateinit var currentPager: Filter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.gas_station_list_view,null)
        setupViewPager(rootView)
        return rootView
    }

    private fun setupViewPager(view: View) {
        pagerAdapter = ViewPagerAdapter(childFragmentManager)
        view.viewpager.adapter = pagerAdapter
        view.tablayout.setupWithViewPager(view.viewpager)

        view.viewpager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener(){
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                currentPager = pagerAdapter.getItem(position) as ItemListFragment
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentPager = pagerAdapter.getItem(position) as ItemListFragment
            }
        })
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
                currentPager.sortByPrice()
            }
            R.id.sort2 ->{
                currentPager.filterUnknown()
            }
            R.id.sort3 ->{
                currentPager.resetList()
            }
            else ->
                return super.onOptionsItemSelected(item)
        }
        return true
    }
}