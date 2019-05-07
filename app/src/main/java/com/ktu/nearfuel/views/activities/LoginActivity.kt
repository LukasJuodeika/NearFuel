package com.ktu.nearfuel.views.activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.ktu.components.contracts.LoginContract
import com.ktu.components.presenters.LoginPresenter
import com.ktu.nearfuel.R
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, LoginContract.View{

    //Vars
    private lateinit var navController: NavController
    private lateinit var auth: FirebaseAuth
    private lateinit var mPresenter : LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //Set instances
        auth = FirebaseAuth.getInstance()
        mPresenter = LoginPresenter(this)

        setupNavigation()
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        //updateUI(currentUser)
    }

    private fun updateUI(currentUser : FirebaseUser){

    }
    private fun setupNavigation() {

        /*setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)*/

        navController = Navigation.findNavController(this, R.id.login_host_fragment)

        //NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)

        NavigationUI.setupWithNavController(navigationView, navController)

        navigationView.setNavigationItemSelectedListener(this)

    }

    override fun navigate(id: Int) {
        navController.navigate(id)
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        p0.isChecked = true

        val id = p0.itemId

        when (id) {
            R.id.loginFragment ->
                mPresenter.onNavigationItemClick(R.id.action_signUpFragment_to_loginFragment)

            //R.id.help ->
            //navController.navigate(R.id.secondFragment)

            R.id.signUpFragment->
                mPresenter.onNavigationItemClick(R.id.action_loginFragment_to_signUpFragment)
        }
        return true
    }




















    private fun createAccount(email: String, password : String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }

                // ...
            }
    }
    private fun signIn(email : String, password : String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }

                // ...
            }
    }

    fun getCurrentUser(){
        val user = FirebaseAuth.getInstance().currentUser
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
        const val TAG = "LoginActivity"
    }
}