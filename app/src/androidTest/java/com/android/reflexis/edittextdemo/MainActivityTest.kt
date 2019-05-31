package com.android.reflexis.edittextdemo


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val customEditText = onView(
            allOf(
                withId(R.id.edit_query),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        customEditText.perform(click())

        val customEditText2 = onView(
            allOf(
                withId(R.id.edit_query),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        customEditText2.perform(replaceText("asdadd"), closeSoftKeyboard())

        val customEditText3 = onView(
            allOf(
                withId(R.id.edit_query), withText("asdadd"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        customEditText3.perform(click())

        val customEditText4 = onView(
            allOf(
                withId(R.id.edit_query), withText("asdadd"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        customEditText4.perform(replaceText("asdaddaa"))

        val customEditText5 = onView(
            allOf(
                withId(R.id.edit_query), withText("asdaddaa"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        customEditText5.perform(closeSoftKeyboard())

        val customEditText6 = onView(
            allOf(
                withId(R.id.edit_query), withText("asdaddaa"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        customEditText6.perform(click())

        val customEditText7 = onView(
            allOf(
                withId(R.id.edit_query), withText("asdaddaa"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        customEditText7.perform(replaceText("asdaddaad"))

        val customEditText8 = onView(
            allOf(
                withId(R.id.edit_query), withText("asdaddaad"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        customEditText8.perform(closeSoftKeyboard())

        val customEditText9 = onView(
            allOf(
                withId(R.id.edit_query), withText("asdaddaad"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        customEditText9.perform(pressImeActionButton())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
