package com.example.the_name_quiz_app.main

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.the_name_quiz_app.R

import org.junit.Before
import org.junit.Test

class MainActivityTest {

    lateinit var activityScenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun testNavigation(){
        onView(withId(R.id.game_fragment)).perform(click())
        onView(withId(R.id.game_fragment_container)).check(matches(isDisplayed()))
        pressBack()
        onView(withId(R.id.people_fragment_container)).check(matches(isDisplayed()))
    }
}