package com.ktu.nearfuel

import com.google.firebase.auth.FirebaseAuth
import com.ktu.nearfuel.login.contracts.LoginContract
import com.ktu.nearfuel.login.presenters.LoginPresenter
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class LoginPresenterTest {

    @Mock
    private lateinit var mockView: LoginContract.View
    @Mock
    private lateinit var mockAuth: FirebaseAuth

    private lateinit var presenter: LoginPresenter

    @Before
    fun setUp() {
        presenter = LoginPresenter(mockView, mockAuth)
    }

    @Test
    fun onLoginClicked_emptyData_displayError() {
        val email = ""
        val password = ""
        presenter.onLoginClicked(email, password)
        Mockito.verify(mockView).displayBlankFieldError()
    }

    @Test
    fun onLoginClicked_emptyEmail_displayError() {
        val email = ""
        val password = "password"
        presenter.onLoginClicked(email, password)
        Mockito.verify(mockView).displayBlankFieldError()
    }

    @Test
    fun onLoginClicked_emptyPassword_displayError() {
        val email = "email"
        val password = ""
        presenter.onLoginClicked(email, password)
        Mockito.verify(mockView).displayBlankFieldError()
    }

    @Test
    fun onLoginClicked_whitespaces_displayError() {
        val email = "         "
        val password = "   "
        presenter.onLoginClicked(email, password)
        Mockito.verify(mockView).displayBlankFieldError()
    }

    @Test
    fun onNavigationItemClicked_navigate() {
        val id = 0
        presenter.onNavigationItemClicked(id)
        Mockito.verify(mockView).navigate(id)
    }
    /*@Test
    fun onLoginClicked_notEmpty_login(){
        val email = "email"
        val password = "password"
        presenter.onLoginClicked(email, password)
        Mockito.verify(presenter).authenticate(email, password)
        Mockito.verify(mockAuth).createUserWithEmailAndPassword(email, password).addOnCompleteListener {  }
    }*/
}