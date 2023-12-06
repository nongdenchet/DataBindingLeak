package com.example.databindingleak

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import leakcanary.DetectLeaksAfterTestSuccess.Companion.detectLeaksAfterTestSuccessWrapping
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @JvmField
    @Rule
    val rule = RuleChain.emptyRuleChain()
        .detectLeaksAfterTestSuccessWrapping(tag = "ActivitiesDestroyed") {
            around(ActivityScenarioRule(MainActivity::class.java))
        }

    @Test
    fun test() {
        onView(withId(R.id.edtMain)).perform(typeText("Tada"))
        onView(withText("Tada")).check(matches(isDisplayed()))

        onView(withId(R.id.btnNext)).check(matches(isDisplayed()))
        onView(withId(R.id.btnNext)).perform(click())

        onView(withId(R.id.btnBack)).check(matches(isDisplayed()))
        onView(withId(R.id.btnBack)).perform(click())

        onView(withId(R.id.btnNext)).check(matches(isDisplayed()))
    }
}
