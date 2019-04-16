package com.ktu.nearfuel.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.navigation.NavigationView
import com.ktu.components.contracts.MainContract
import com.ktu.components.presenters.MainPresenter
import com.ktu.nearfuel.R
import com.ktu.nearfuel.ui.main.presenter.MapsNewContract
import com.ktu.nearfuel.ui.main.view.MainMVPView
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainContract.View{
    //override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector

    private lateinit var navController: NavController
    private lateinit var presenter: MainContract.Presenter

    @Inject
    internal lateinit var presenter1: MapsNewContract<MainMVPView>
//    @Inject
//    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
       // setTheme(R.style.AppTheme)
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)
        presenter1.getStationsNearLocation(latLng = LatLng (21.0,24.5))

        setupNavigation()
    }



    override fun onNavigationItemSelected(p0: MenuItem): Boolean {

        p0.isChecked = true

        drawer_layout.closeDrawers()

        val id = p0.itemId

        when (id) {
            R.id.gas_station_list ->
                presenter.onNavigationItemClick(R.id.action_mapFragment_to_gasStationListFragment)

            //R.id.help ->
            //navController.navigate(R.id.secondFragment)

            R.id.settings->
                presenter.onNavigationItemClick(R.id.action_mapFragment_to_settingsFragment)
        }
        return true
    }

    override fun navigate(id: Int) {
        navController.navigate(id)
    }

    private fun setupNavigation() {

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)

        NavigationUI.setupWithNavController(navigationView, navController)

        navigationView.setNavigationItemSelectedListener(this)

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment), drawer_layout)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
