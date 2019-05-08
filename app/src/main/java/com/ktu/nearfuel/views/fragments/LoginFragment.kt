package com.ktu.nearfuel.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.ktu.components.contracts.LoginContract
import com.ktu.components.presenters.LoginPresenter
import com.ktu.nearfuel.R
import kotlinx.android.synthetic.main.fragment_login.view.*



class LoginFragment : Fragment(), LoginContract.View {

    private lateinit var mPresenter: LoginPresenter
    private lateinit var mNavigation : NavController
    private lateinit var mAuth : FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        mAuth = FirebaseAuth.getInstance()
        mPresenter = LoginPresenter(this, mAuth)
        mNavigation = findNavController()
        setClickListeners(view)
        return view
    }

    private fun setClickListeners(view: View){
        view.link_sign_up.setOnClickListener{
            mPresenter.onNavigationItemClicked(R.id.action_loginFragment_to_signUpFragment)
        }
        view.btn_login.setOnClickListener {
            val email = view.input_email.text.toString()
            val password = view.input_password.text.toString()
            mPresenter.onLoginClicked(email, password)
        }
    }

    override fun navigate(id: Int) {
        mNavigation.navigate(id)
    }

    override fun login() {
        navigate(R.id.action_loginFragment_to_mainActivity)
    }

    override fun displayError(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}