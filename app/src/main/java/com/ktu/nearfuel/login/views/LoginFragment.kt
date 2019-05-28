package com.ktu.nearfuel.login.views

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
import com.ktu.components.data.FuelType
import com.ktu.nearfuel.R
import com.ktu.nearfuel.itemList.contracts.ItemListContract
import com.ktu.nearfuel.itemList.views.ItemListFragment
import com.ktu.nearfuel.login.contracts.LoginContract
import com.ktu.nearfuel.login.presenters.LoginPresenter
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_login.view.*
import java.lang.ClassCastException
import javax.inject.Inject


class LoginFragment: Fragment(), LoginContract.View {

    //Variables
    private lateinit var mNavigation : NavController
    private lateinit var mAuth : FirebaseAuth
    private lateinit var mCallback: OnLoginListener//Callback for finishing the activity

    //UI
    private lateinit var mProgressBar : ProgressBar

    @Inject
    lateinit var presenter: LoginContract.Presenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        mAuth = FirebaseAuth.getInstance()
        mNavigation = findNavController()
        setLayouts(view)
        setClickListeners(view)
        return view
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
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
            presenter.onNavigationItemClicked(R.id.action_loginFragment_to_signUpFragment)
        }
        view.btn_login.setOnClickListener {
            val email = view.input_email.text.toString()
            val password = view.input_password.text.toString()
            presenter.onLoginClicked(email, password)
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

    override fun displayEmailVerificationError() {
        Toast.makeText(activity, getString(R.string.email_verification_error), Toast.LENGTH_LONG).show()
    }

    interface OnLoginListener{
        fun onLogin()
    }
}