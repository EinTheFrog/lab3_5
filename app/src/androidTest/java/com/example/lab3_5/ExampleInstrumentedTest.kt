package com.example.myapplication

import android.view.Gravity
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.contrib.DrawerActions
import com.example.lab3_5.MainActivity
import com.example.lab3_5.R
import androidx.test.espresso.contrib.DrawerMatchers.isClosed
import androidx.test.espresso.contrib.NavigationViewActions
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class NavigationTest {
    @Test
    fun testAbout() {
        launchActivity<MainActivity>()
        openAbout()
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
    }

    @Test
    fun testFragment1() {
        launchActivity<MainActivity>()
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
    }

    @Test
    fun testFragment2() {
        launchActivity<MainActivity>()
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
    }

    @Test
    fun testFragment3() {
        launchActivity<MainActivity>()
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
    }

    @Test
    fun testBackFromSecond1() {
        launchActivity<MainActivity>()
        onView(withId(R.id.bnToSecond)).perform(click())
        pressBack()
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
    }

    @Test
    fun testBackFromSecond2() {
        launchActivity<MainActivity>()
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withId(R.id.bnToSecond)).perform(click())
        pressBack()
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
    }

    @Test
    fun testBackFromThird() {
        launchActivity<MainActivity>()
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        pressBack()
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
    }

    @Test
    fun testBackFromAbout1() {
        launchActivity<MainActivity>()
        openAbout()
        pressBack()
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
    }

    @Test
    fun testBackFromAbout2() {
        launchActivity<MainActivity>()
        onView(withId(R.id.bnToSecond)).perform(click())
        openAbout()
        pressBack()
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
    }

    @Test
    fun testBackFromAbout3() {
        launchActivity<MainActivity>()
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        openAbout()
        pressBack()
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
    }

    @Test
    fun testOrientationChange1() {
        val activityScenario = launchActivity<MainActivity>()
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        activityScenario.recreate()
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
    }

    @Test
    fun testOrientationChange2() {
        val activityScenario = launchActivity<MainActivity>()
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        activityScenario.recreate()
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
    }

    @Test
    fun testOrientationChange3() {
        val activityScenario = launchActivity<MainActivity>()
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
        activityScenario.recreate()
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
    }

    private fun openAbout() {
        onView(withId(R.id.drawer_layout))
            .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
            .perform(DrawerActions.open()); // Open Drawer

        // Start the screen of your activity.
        onView(withId(R.id.bnToAbout)).perform(click())
    }
}