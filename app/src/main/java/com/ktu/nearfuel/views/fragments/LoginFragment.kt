package com.ktu.nearfuel.views.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.ktu.components.contracts.LoginContract
import com.ktu.components.presenters.LoginPresenter
import com.ktu.nearfuel.R
import kotlinx.android.synthetic.main.fragment_login.view.*
import java.lang.ClassCastException


class LoginFragment: Fragment(), LoginContract.View {

    //Variables
    private lateinit var mPresenter: LoginPresenter
    private lateinit var mNavigation : NavController
    private lateinit var mAuth : FirebaseAuth
    private lateinit var mCallback: OnLoginListener//Callback for finishing the activity

    //UI
    private lateinit var mProgressBar : ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        mAuth = FirebaseAuth.getInstance()
        mPresenter = LoginPresenter(this, mAuth)
        mNavigation = findNavController()
        setLayouts(view)
        setClickListeners(view)
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mCallback = context as OnLoginListener
        } catch (e : ClassCastException){
            throw ClassCastException(activity.toString()
                    + " must implement OnLoginListener")
        }
    }

    private fun setLayouts(view: View){
        mProgressBar = view.progress_bar
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
        mCallback.onLogin()
    }

    override fun showProgress() {
        mProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        mProgressBar.visibility = View.GONE
    }

    override fun displayError(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun displayBlankFieldError() {
        Toast.makeText(activity, getString(R.string.blank_fields), Toast.LENGTH_SHORT).show()
    }

    override fun displayGenericError() {
        Toast.makeText(activity, getString(R.string.auth_failed), Toast.LENGTH_SHORT).show()
    }

    interface OnLoginListener{
        fun onLogin()
    }
}