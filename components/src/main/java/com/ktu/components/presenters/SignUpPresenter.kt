package com.ktu.components.presenters

import com.ktu.components.contracts.SignUpContract
import com.google.firebase.auth.FirebaseAuth

class SignUpPresenter(val view: SignUpContract.View, private val mAuth : FirebaseAuth): SignUpContract.Presenter {

    override fun onSignUpClicked(email: String, password: String) {
        if (email.isNotBlank() && password.isNotBlank()){
            createAccount(email, password)
        }else{
            view.displayBlankFieldError()
        }
    }

    override fun onNavigationItemClicked(id: Int) {
        view.navigate(id)
    }

    private fun createAccount(email: String, password : String){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    user?.sendEmailVerification()
                    view.navigateToLogin()
                } else {
                    val exception = task.exception
                    val errorCode : String
                    if(exception != null){
                        errorCode = exception.message.toString()
                        view.displayError(errorCode)
                    }else{
                        view.displayGenericError()
                    }

                }
            }
    }
}