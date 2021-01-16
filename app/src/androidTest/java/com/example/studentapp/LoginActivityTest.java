package com.example.studentapp;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4ClassRunner.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>
            (LoginActivity.class);

    @Test
    public void usernameIsInvalid(){
        onView(withId(R.id.txtEmail)).perform(clearText());
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withText(R.string.TOAST_STRING)).inRoot(withDecorView(not(is
                (activityTestRule.getActivity().getWindow().getDecorView())))).check(matches
                (isDisplayed()));
    }

    @Test
    public void passwordIsInvalid(){
        onView(withId(R.id.txtEmail)).perform(typeText("john.doe@gmail.com"),
                closeSoftKeyboard());
        onView(withId(R.id.txtPwd)).perform(clearText());
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withText(R.string.TOAST_STRING)).inRoot(withDecorView(not(is(activityTestRule.
                getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

}
