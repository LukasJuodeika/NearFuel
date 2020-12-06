package com.ktu.nearfuel.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.ktu.components.contracts.SignUpContract
import com.ktu.nearfuel.login.presenters.SignUpPresenter
import com.ktu.nearfuel.R
import com.ktu.nearfuel.network.APIInterface
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_signup.view.*
import javax.inject.Inject
import javax.inject.Named


class SignUpFragment : Fragment(), SignUpContract.View {

    private lateinit var mPresenter: SignUpPresenter
    private lateinit var mNavigation: NavController

    @Inject
    @Named("unauthorized")
    lateinit var apiInterface: APIInterface

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_signup, container, false)
        mPresenter = SignUpPresenter(this, apiInterface)
        mNavigation = findNavController()
        setClickListeners(view)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
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