package com.saravana.feedapplication.feedlist.activity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.saravana.feedapplication.R
import com.saravana.feedapplication.feedlist.adapter.FeedItemViewHolder
import com.saravana.feedapplication.feedlist.constant.BundleConstant
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FeedActivityTest {

    @get:Rule
    var intentsRule: IntentsTestRule<FeedListActivity> =
        IntentsTestRule(FeedListActivity::class.java)

    @Test
    fun verifyFeedSentToFeedDetailActivity() {
        onView(withId(R.id.feedListRecyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<FeedItemViewHolder>(
                0,
                click()
            )
        )

        intended(hasExtraWithKey(BundleConstant.KEY_FEED))

    }

    @Test
    fun verifyFeedListViewIsDisplayed() {
        onView(withId(R.id.feedListRecyclerView)).check(matches(isDisplayed()))
    }
}
