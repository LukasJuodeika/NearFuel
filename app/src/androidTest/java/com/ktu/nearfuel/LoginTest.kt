package com.ktu.nearfuel

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.ktu.nearfuel.views.activities.AuthenticationActivity
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class LoginTest {

    @get:Rule
    var intentsTestRule = IntentsTestRule(AuthenticationActivity::class.java)

    private val email = "test@gmail.com"
    private val pass = "testest"

    /*@Test
    fun login(){
        onView(withId(R.id.input_email))
            .perform(typeText(email), closeSoftKeyboard())
        onView(withId(R.id.input_password))
            .perform(typeText(pass), closeSoftKeyboard())
        onView(withId(R.id.btn_login))
            .perform(click())

        //intended(hasComponent(MainActivity::class.simpleName))
    }*/
    @Test
    fun emailIsEmpty_login(){
        val activity = intentsTestRule.activity
        onView(withId(R.id.input_email)).perform(clearText())
        onView(withId(R.id.btn_login)).perform(click())
        onView(withText(R.string.blank_fields)).
            inRoot(withDecorView(not(activity.window.decorView)))
            .check(matches(isDisplayed()))
    }
    @Test
    fun emailIsBadlyFormatted_login(){
        val activity = intentsTestRule.activity
        onView(withId(R.id.input_email)).perform(clearText())
            .perform(typeText("badlyformattedemail"))
        onView(withId(R.id.input_password)).perform(clearText())
            .perform(typeText("random"))
        Espresso.pressBack()
        onView(withId(R.id.btn_login)).perform(click())
        onView(withText("The email address is badly formatted.")).
            inRoot(withDecorView(not(activity.window.decorView)))
            .check(matches(isDisplayed()))
    }
    @Test
    fun incorrectPassword_login(){
        val activity = intentsTestRule.activity
        onView(withId(R.id.input_email)).perform(clearText())
            .perform(typeText(email))
        onView(withId(R.id.input_password)).perform(clearText())
            .perform(typeText("random"))
        Espresso.pressBack()
        onView(withId(R.id.btn_login)).perform(click())
        onView(withText("The password is invalid or the user does not have a password.")).
            inRoot(withDecorView(not(activity.window.decorView)))
            .check(matches(isDisplayed()))
    }

}