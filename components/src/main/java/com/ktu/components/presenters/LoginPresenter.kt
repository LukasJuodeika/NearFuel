package com.ktu.components.presenters

import com.google.firebase.auth.FirebaseAuth
import com.ktu.components.contracts.LoginContract

class LoginPresenter(val view: LoginContract.View, private val mAuth: FirebaseAuth) : LoginContract.Presenter {

    override fun onLoginClicked(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()){
            view.showProgress()
            authenticate(email, password)
        }else{
            view.displayBlankFieldsError()
        }
    }

    override fun onNavigationItemClicked(id: Int) {
        view.navigate(id)
    }

    private fun authenticate(email : String, password : String){
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = mAuth.currentUser
                    view.login()
                } else {
                    val exception = task.exception
                    val errorCode : String
                    if(exception != null){
                        errorCode = exception.message.toString()
                        view.displayError(errorCode)
                    }else{
                        view.displayGenericError()
                    }
                    view.hideProgress()
                }
                // ...
            }
    }
}