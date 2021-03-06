package com.ktu.nearfuel.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.ktu.components.contracts.MainContract
import com.ktu.nearfuel.MainPresenter
import com.ktu.nearfuel.R
import com.ktu.nearfuel.login.views.AuthenticationActivity
import com.ktu.nearfuel.maps.contracts.MapsNewContract
import com.ktu.nearfuel.maps.views.MainMVPView
import com.ktu.nearfuel.network.AuthRepository
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    MainContract.View, HasSupportFragmentInjector {


    private lateinit var navController: NavController
    private lateinit var presenter: MainContract.Presenter

    @Inject
    internal lateinit var presenter1: MapsNewContract<MainMVPView>

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    internal lateinit var authRepository: AuthRepository


    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this, authRepository)
        presenter.onCreate()
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

            R.id.settings ->
                presenter.onNavigationItemClick(R.id.action_mapFragment_to_settingsFragment)

            R.id.sign_out ->
                presenter.onSignOutClick()
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
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.nav_host_fragment),
            drawer_layout
        )
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun signOut() {
        val intent = Intent(this, AuthenticationActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun displayEmail(email: String) {
        Toast.makeText(this, "Logged in as $email", Toast.LENGTH_SHORT).show()
    }
}
