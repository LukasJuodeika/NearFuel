package com.ktu.nearfuel.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.ktu.components.contracts.MainContract
import com.ktu.components.presenters.MainPresenter
import com.ktu.nearfuel.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainContract.View {

    private lateinit var navController: NavController
    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
       // setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)

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
