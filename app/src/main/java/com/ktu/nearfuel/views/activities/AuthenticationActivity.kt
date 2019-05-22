package com.ktu.nearfuel.views.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ktu.components.presenters.AuthenticationPresenter
import com.ktu.nearfuel.R
import com.ktu.components.contracts.AuthenticationContract
import com.ktu.nearfuel.util.PermissionHandler
import com.ktu.nearfuel.views.fragments.LoginFragment


class AuthenticationActivity : AppCompatActivity(), AuthenticationContract.View, LoginFragment.OnLoginListener{

    //Variables
    private lateinit var mPresenter : AuthenticationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
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