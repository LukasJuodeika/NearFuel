package com.ktu.components.presenters

import com.google.firebase.auth.FirebaseAuth
import com.ktu.components.contracts.LoginContract

class LoginPresenter(val view: LoginContract.View, val mAuth: FirebaseAuth) : LoginContract.Presenter {

    override fun onLoginClicked(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()){
            login(email, password)
        }else{
            view.displayError("All fields must be filled in.")
        }
    }

    override fun onNavigationItemClicked(id: Int) {
        view.navigate(id)
    }

    private fun login(email : String, password : String){
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = mAuth.currentUser
                    view.login()
                } else {
                    // If sign in fails, display a message to the user.
                    view.displayError("Authentication failed.")
                }

                // ...
            }
    }
}