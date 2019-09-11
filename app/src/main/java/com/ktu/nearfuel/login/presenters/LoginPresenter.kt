package com.ktu.nearfuel.login.presenters

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.ktu.components.data.GasStationDao
import com.ktu.components.objects.GasStation
import com.ktu.nearfuel.itemList.contracts.ItemListContract
import com.ktu.nearfuel.login.contracts.LoginContract
import com.ktu.nearfuel.network.APIInterface
import com.ktu.nearfuel.network.Resource
import com.ktu.nearfuel.network.models.LoginRequestBody
import com.ktu.nearfuel.rx.SchedulersFacade
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginPresenter @Inject constructor(
    private val view: LoginContract.View,
    private val schedulersFacade: SchedulersFacade,
    val mAuth: FirebaseAuth,
    val apiInterface: APIInterface
) :
    LoginContract.Presenter {

    private val disposables = CompositeDisposable()

    override fun onLoginClicked(email: String, password: String) {
        if (email.isNotBlank() && password.isNotBlank()) {
            view.showProgress()
            authenticate(email, password)
        } else {
            view.displayBlankFieldError()
        }
    }

    override fun onNavigationItemClicked(id: Int) {
        view.navigate(id)
    }

    fun loginUserAPI(email: String, uid: String) {
        disposables.add(apiInterface.loginUser(
            LoginRequestBody(email, uid)
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.login()

            },
                {
                    Log.d("responseerror", it.message)

                }, {

                }
            ))

    }


    private fun authenticate(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    if (user != null && user.isEmailVerified)
                        loginUserAPI(email, user.uid)
                    else {
                        view.displayEmailVerificationError()
                        view.hideProgress()
                    }
                } else {
                    val exception = task.exception
                    val errorCode: String
                    if (exception != null) {
                        errorCode = exception.message.toString()
                        view.displayError(errorCode)
                    } else {
                        view.displayGenericError()
                    }
                    view.hideProgress()
                }
                // ...
            }
    }
}