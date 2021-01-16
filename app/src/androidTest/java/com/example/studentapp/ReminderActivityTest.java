package com.example.studentapp;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4ClassRunner.class)
public class ReminderActivityTest {

    @Rule
    public ActivityTestRule<ReminderActivity> activityTestRule = new ActivityTestRule<>(ReminderActivity.class);

    @Test
    public void invalidTime(){
        /*Calendar rightNow = Calendar.getInstance();
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setTime(rightNow.get(Calendar.HOUR_OF_DAY)-1, 0));
        onView(withText(R.string.TOAST_STRING3)).inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));*/
        //click + btn
        onView(withId(R.id.floatingButton)).perform(click());
        //click select
        onView(withId(R.id.selectDate)).perform(click());
        //pick today's date
        //onView()
        //pick any time before now
        //check for toast
    }

}
