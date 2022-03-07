package com.example.the_name_quiz_app.game

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.the_name_quiz_app.R
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Test

class GameFragmentTest {

    private lateinit var scenarioGame: FragmentScenario<GameFragment>

    @Before
    fun setUp() {
        scenarioGame = launchFragmentInContainer(themeResId = R.style.Theme_App)
        scenarioGame.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun testGame() {
        onView(withId(R.id.count)).check(matches(withText("0")))
        onView(withId(R.id.name1)).perform(click())
        onView(withId(R.id.count)).check(matches(withText("0")))
        onView(withId(R.id.name1)).perform(click())
        onView(withId(R.id.count)).check(matches(withText(not("0"))))
    }
}