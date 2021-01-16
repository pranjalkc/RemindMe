package com.example.studentapp;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
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
public class RegisterationActivityTest {

    @Rule
    public ActivityTestRule<RegistrationActivity> activityTestRule = new ActivityTestRule<>(RegistrationActivity.class);

    @Test
    public void userExists(){
        onView(withId(R.id.editText)).perform(typeText("john.doe@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.editText2)).perform(typeText("abcd"), closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());
        onView(withText(R.string.TOAST_STRING2)).inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

}
