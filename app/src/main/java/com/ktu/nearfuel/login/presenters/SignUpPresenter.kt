package com.ktu.nearfuel.login.presenters

import com.ktu.components.contracts.SignUpContract
import com.ktu.nearfuel.network.APIInterface
import com.ktu.nearfuel.network.models.RegistrationRequestBody
import io.reactivex.disposables.CompositeDisposable

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
            ).subscribe({
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