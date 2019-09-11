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
import com.ktu.components.contracts.SignUpContract
import com.ktu.components.presenters.SignUpPresenter
import com.ktu.nearfuel.R
import kotlinx.android.synthetic.main.fragment_signup.view.*


class SignUpFragment : Fragment(), SignUpContract.View {

    private lateinit var mPresenter: SignUpPresenter
    private lateinit var mNavigation: NavController
    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_signup, container, false)
        mAuth = FirebaseAuth.getInstance()
        mPresenter = SignUpPresenter(this, mAuth)
        mNavigation = findNavController()
        setClickListeners(view)
        return view
    }

    private fun setClickListeners(view: View) {
        view.link_login.setOnClickListener {
            mPresenter.onNavigationItemClicked(R.id.action_signUpFragment_to_loginFragment)
        }
        view.btn_signup.setOnClickListener {
            val email = view.input_email.text.toString()
            val password = view.input_password.text.toString()
            mPresenter.onSignUpClicked(email, password)
        }
    }

    override fun navigate(id: Int) {
        mNavigation.navigate(id)
    }

    override fun navigateToLogin() {
        navigate(R.id.action_signUpFragment_to_loginFragment)
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
}