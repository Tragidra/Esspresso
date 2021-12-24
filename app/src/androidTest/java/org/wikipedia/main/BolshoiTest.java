package org.wikipedia.main;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wikipedia.R;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class BolshoiTest {

    String login = "Tase011";
    String password = "astrafaz99";
    private UiDevice mUiDevice;


    @Rule
    public ActivityScenarioRule<MainActivity> activityRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void initValidString() {
        mUiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        String stringToBetyped = "Espresso";

    }


    public static Activity getCurrentActivity() {
        final Activity[] activity = new Activity[1];

        onView(isRoot()).check((view, noViewFoundException) -> {

            View checkedView = view;

            while (checkedView instanceof ViewGroup && ((ViewGroup) checkedView).getChildCount() > 0) {

                checkedView = ((ViewGroup) checkedView).getChildAt(0);

                if (checkedView.getContext() instanceof Activity) {
                    activity[0] = (Activity) checkedView.getContext();
                    return;
                }
            }
        });
        return activity[0];
    }

    @Test
    public void alogin() throws InterruptedException, UiObjectNotFoundException {

        onView(withId(R.id.fragment_onboarding_skip_button))
                .perform(click());

        onView(withId(R.id.nav_more_container))
                .perform(click());

        onView(withId(R.id.main_drawer_login_button))
                .perform(click());

        onView(withId(R.id.create_account_login_button))
                .perform(click());

        onView(allOf(withId(R.id.login_username_text), isDisplayed()))
                .perform(click());

        /* Это клик по автозаполнению, Trase011 - мой логин в Википедии, при запуске не на моём аккаунте гугл надо указать логин другого аккаунта*/
        UiObject mText = mUiDevice.findObject(new UiSelector().text("Trase011"));
        mText.click();

        onView(withId(R.id.login_button))
                .perform(click());

        IdlingResource idlingResource = new ElapsedTimeIdlingResource(4);
        Espresso.registerIdlingResources(idlingResource);

        onView(withId(R.id.view_wiki_error_button))
                .perform(click());

    }

    @Test
    public void bmenu() throws UiObjectNotFoundException {
    onView(withId(R.id.fragment_onboarding_skip_button))
    .perform(click());

    UiObject mText1 = mUiDevice.findObject(new UiSelector().text("Explore"));
    mText1.click();

    UiObject mText2 = mUiDevice.findObject(new UiSelector().text("Saved"));
    mText2.click();

    UiObject mText3 = mUiDevice.findObject(new UiSelector().text("Search"));
    mText3.click();

    UiObject mText4 = mUiDevice.findObject(new UiSelector().text("Edits"));
    mText4.click();

    }

    @Test
    public void cvihod() {
        onView(withId(R.id.fragment_onboarding_skip_button))
                .perform(click());

        onView(withId(R.id.nav_more_container))
                .perform(click());

        onView(withId(R.id.main_drawer_settings_container))
                .perform(click());

        onView(allOf(withId(R.id.recycler_view)))
                .perform(swipeUp());

        onView(withId(R.id.logoutButton))
                .perform(click());

        onView(withText("LOG OUT"))
                .perform(click());
    }


    @Test
    public void search() throws InterruptedException {
        onView(withId(R.id.fragment_onboarding_skip_button))
                .perform(click());


        // Click the Search box
        onView(allOf(withId(R.id.search_container), isDisplayed()))
                .perform(click());

        // Type in our search term
        onView(allOf(withId(R.id.search_src_text), isDisplayed()))
                .perform(replaceText("Kazan"), closeSoftKeyboard());

        Thread.sleep(1000);

        onView(allOf(withId(R.id.page_list_item_title), withText("Kazan"), isDisplayed()))
                .check(matches(withText("Kazan")));

        onView(allOf(withId(R.id.page_list_item_title), withText("Kazan"), isDisplayed()))
                .check(matches(withText("Kazan")));

        onView(withId(R.id.search_results_list))
                .perform(actionOnItemAtPosition(0, click()));

        onView(withId(R.id.article_menu_bookmark))
                .perform(click());

        onView(withId(R.id.page_toolbar_button_tabs))
                .perform(click());

        pressBack();
    }


}


