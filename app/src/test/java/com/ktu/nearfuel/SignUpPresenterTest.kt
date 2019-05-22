package com.ktu.nearfuel

import com.google.firebase.auth.FirebaseAuth
import com.ktu.components.contracts.SignUpContract
import com.ktu.components.presenters.SignUpPresenter
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class SignUpPresenterTest {

    @Mock
    private lateinit var mockView : SignUpContract.View
    @Mock
    private lateinit var mockAuth : FirebaseAuth

    private lateinit var presenter: SignUpPresenter

    @Before
    fun setUp(){
        presenter = SignUpPresenter(mockView, mockAuth)
    }

    @Test
    fun onSignUpClicked_emptyData_displayError(){
        val email = ""
        val password = ""
        presenter.onSignUpClicked(email, password)
        Mockito.verify(mockView).displayBlankFieldError()
    }
    @Test
    fun onSignUpClicked_emptyEmail_displayError(){
        val email = ""
        val password = "password"
        presenter.onSignUpClicked(email, password)
        Mockito.verify(mockView).displayBlankFieldError()
    }
    @Test
    fun onSignUpClicked_emptyPassword_displayError(){
        val email = "email"
        val password = ""
        presenter.onSignUpClicked(email, password)
        Mockito.verify(mockView).displayBlankFieldError()
    }
    @Test
    fun onSignUpClicked_whitespaces_displayError(){
        val email = "         "
        val password = "   "
        presenter.onSignUpClicked(email, password)
        Mockito.verify(mockView).displayBlankFieldError()
    }
    @Test
    fun onNavigationItemClicked_navigate(){
        val id = 0
        presenter.onNavigationItemClicked(id)
        Mockito.verify(mockView).navigate(id)
    }
    /*@Test
    fun onSignUpClicked_notEmpty_createAccount(){
        val email = "email"
        val password = "password"
        presenter.onSignUpClicked(email, password)
        Mockito.verify(presenter).createAccount(email, password)
        Mockito.verify(mockAuth).createUserWithEmailAndPassword(email, password).addOnCompleteListener {  }
    }*/
}