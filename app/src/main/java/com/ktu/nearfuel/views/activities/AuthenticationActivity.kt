package com.ktu.nearfuel.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.ktu.components.presenters.AuthenticationPresenter
import com.ktu.nearfuel.R
import com.ktu.components.contracts.AuthenticationContract


class AuthenticationActivity : AppCompatActivity(), AuthenticationContract.View{

    //Vars
    private lateinit var mNavigation : NavController
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mPresenter : AuthenticationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //Set instances
        mAuth = FirebaseAuth.getInstance()
        mPresenter = AuthenticationPresenter(this)
        mNavigation = findNavController(R.id.login_host_fragment)
    }

    override fun onStart() {
        super.onStart()
        //val currentUser = mAuth.currentUser
        //updateUI(currentUser)
    }

    fun getCurrentUser(){
        val user = mAuth.currentUser
        user?.let {
            // Name, email address, and profile photo Url
            val name = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl

            // Check if user's email is verified
            val emailVerified = user.isEmailVerified

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            val uid = user.uid
        }
    }

    companion object{
        const val TAG = "AuthenticationActivity"
    }
}