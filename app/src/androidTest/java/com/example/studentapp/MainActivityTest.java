package com.example.studentapp;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);



    @Before
    public void setUp() throws Exception {
        Intents.init();
    }

    @Test
    public void testUserClickScenario(){
        Espresso.onView(withId(R.id.t1)).perform(click());
        intended(hasComponent(ReminderActivity.class.getName()));
        Espresso.pressBack();
        Espresso.onView(withId(R.id.t2)).perform(click());
        intended(hasComponent(ToDoListActivity.class.getName()));
        Espresso.pressBack();
        Espresso.onView(withId(R.id.t3)).perform(click());
        intended(hasComponent(GalleryActivity.class.getName()));
        Espresso.pressBack();
    }

    @After
    public void tearDown() throws Exception {
    }
}