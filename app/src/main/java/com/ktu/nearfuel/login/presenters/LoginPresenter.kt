package com.ktu.nearfuel.login.presenters

import com.google.firebase.auth.FirebaseAuth
import com.ktu.components.data.GasStationDao
import com.ktu.nearfuel.itemList.contracts.ItemListContract
import com.ktu.nearfuel.login.contracts.LoginContract
import com.ktu.nearfuel.network.APIInterface
import com.ktu.nearfuel.rx.SchedulersFacade
import javax.inject.Inject

class LoginPresenter    @Inject constructor(
    private val view: LoginContract.View,
    private val schedulersFacade: SchedulersFacade,
    val mAuth: FirebaseAuth,
    val apiInterface: APIInterface
) :
    LoginContract.Presenter {

    override fun onLoginClicked(email: String, password: String) {
        if (email.isNotBlank() && password.isNotBlank()){
            view.showProgress()
            authenticate(email, password)
        }else{
            view.displayBlankFieldError()
        }
    }

    override fun onNavigationItemClicked(id: Int) {
        view.navigate(id)
    }

    fun loginUser(uid:String)
    {

    }



    private fun authenticate(email : String, password : String){
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    if(user != null && user.isEmailVerified)
                        view.login()
                    else{
                        view.displayEmailVerificationError()
                        view.hideProgress()
                    }
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