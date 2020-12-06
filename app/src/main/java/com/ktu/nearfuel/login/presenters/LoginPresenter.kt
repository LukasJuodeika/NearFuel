package com.ktu.nearfuel.login.presenters

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.ktu.nearfuel.BuildConfig
import com.ktu.nearfuel.login.contracts.LoginContract
import com.ktu.nearfuel.network.APIInterface
import com.ktu.nearfuel.network.AuthRepository
import com.ktu.nearfuel.rx.SchedulersFacade
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

class LoginPresenter @Inject constructor(
    private val view: LoginContract.View,
    private val schedulersFacade: SchedulersFacade,
    val authRepository: AuthRepository,
    @Named("unauthorized") val apiInterface: APIInterface
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

    private fun authenticate(email: String, password: String) {
        disposables.add(
            apiInterface.login(
                authorization = BuildConfig.CLIENT_AUTH,
                username = email,
                password = password
            ).flatMapCompletable {
                Completable.fromAction {
                    authRepository.setAccessToken(it.accessToken)
                    authRepository.setRefreshToken(it.refreshToken)
                }
            }
                .subscribeOn(schedulersFacade.io())
                .observeOn(schedulersFacade.ui())
                .subscribe({
                    view.login()
                }, { exception ->
                    val errorCode: String
                    if (exception != null) {
                        errorCode = exception.message.toString()
                        view.displayError(errorCode)
                    } else {
                        view.displayGenericError()
                    }
                    view.hideProgress()
                })
        )
    }
}