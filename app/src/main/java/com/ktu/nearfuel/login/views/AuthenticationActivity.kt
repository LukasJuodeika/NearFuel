package com.ktu.nearfuel.login.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ktu.components.presenters.AuthenticationPresenter
import com.ktu.nearfuel.R
import com.ktu.components.contracts.AuthenticationContract
import com.ktu.nearfuel.util.PermissionHandler
import com.ktu.nearfuel.views.activities.MainActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class AuthenticationActivity : AppCompatActivity(), AuthenticationContract.View,
    LoginFragment.OnLoginListener, HasSupportFragmentInjector {
    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>


    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector
    //Variables
    private lateinit var mPresenter : AuthenticationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mPresenter = AuthenticationPresenter(this)
        mPresenter.checkForPermissions()
    }

    override fun onLogin() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }

    override fun requestPermissions(){
        if(PermissionHandler.getMissingPermissions(this).size > 0) {
            PermissionHandler.getPermissions(this)
        }
    }
}