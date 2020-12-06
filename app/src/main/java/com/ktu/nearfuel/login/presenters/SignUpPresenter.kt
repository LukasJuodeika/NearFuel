package com.ktu.nearfuel.login.presenters

import com.ktu.components.contracts.SignUpContract
import com.ktu.nearfuel.network.APIInterface
import com.ktu.nearfuel.network.models.RegistrationRequestBody
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SignUpPresenter(val view: SignUpContract.View, val apiInterface: APIInterface) : SignUpContract.Presenter {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onSignUpClicked(email: String, password: String) {
        if (email.isNotBlank() && password.isNotBlank()) {
            createAccount(email, password)
        } else {
            view.displayBlankFieldError()
        }
    }

    override fun onNavigationItemClicked(id: Int) {
        view.navigate(id)
    }

    private fun createAccount(email: String, password: String) {
        compositeDisposable.add(
            apiInterface.register(
                RegistrationRequestBody(email, password)
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                view.navigateToLogin()
            }, { exception ->
                val errorCode: String
                if (exception != null) {
                    errorCode = exception.message.toString()
                    view.displayError(errorCode)
                } else {
                    view.displayGenericError()
                }
            })
        )
    }
}