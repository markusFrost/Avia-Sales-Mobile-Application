package avia.androi.innopolis.com.aviasales.register;

import android.content.res.Resources;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import avia.androi.innopolis.com.aviasales.MainActivity;
import avia.androi.innopolis.com.aviasales.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

public class RegistrationPresenterTest123 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() throws Exception {


    }

    @Test
    public void testRegister() throws Exception {


    }

    @Test
    public void testEmptyName(){

        onView(withId(R.id.registrationEditName)).perform(typeText(""));
        onView(withId(R.id.registrationButtonOk)).perform(click());
        onView(withId(R.id.registrationTvError)).check(matches(isDisplayed()));
        onView(withId(R.id.registrationTvError)).check(matches(withText(R.string.errorEmptyName)));
    }

    @Test
    public void testEmptyEmail(){

        onView(withId(R.id.registrationEditName)).perform(typeText("Name"));
        onView(withId(R.id.registrationEditEmail)).perform(typeText(""));
        onView(withId(R.id.registrationButtonOk)).perform(click());
        onView(withId(R.id.registrationTvError)).check(matches(isDisplayed()));
        onView(withId(R.id.registrationTvError)).check(matches(withText(R.string.errorEmptyEmail)));
    }

    @Test
    public void testEmptyPassword(){

        onView(withId(R.id.registrationEditName)).perform(typeText("Name"));
        onView(withId(R.id.registrationEditEmail)).perform(typeText("Email"));
        onView(withId(R.id.registrationEditPasswoed)).perform(typeText(""));
        onView(withId(R.id.registrationButtonOk)).perform(click());
        onView(withId(R.id.registrationTvError)).check(matches(isDisplayed()));
        onView(withId(R.id.registrationTvError)).check(matches(withText(R.string.errorEmptyPassword)));
    }

    @Test
    public void testRightData(){

        onView(withId(R.id.registrationEditName)).perform(typeText("Name"));
        onView(withId(R.id.registrationEditEmail)).perform(typeText("Email"));
        onView(withId(R.id.registrationEditPasswoed)).perform(typeText("Password"));
        onView(withId(R.id.registrationButtonOk)).perform(click());
        onView(withId(R.id.registrationTvError)).check(matches(not(isDisplayed())));
    }

    public static Matcher<View> withText(final int resourceId) {

        return new BoundedMatcher<View, TextView>(TextView.class) {
            private String resourceName = null;
            private String expectedText = null;

            @Override
            public void describeTo(Description description) {
                description.appendText("with string from resource id: ");
                description.appendValue(resourceId);
                if (null != this.resourceName) {
                    description.appendText("[");
                    description.appendText(this.resourceName);
                    description.appendText("]");
                }
                if (null != this.expectedText) {
                    description.appendText(" value: ");
                    description.appendText(this.expectedText);
                }
            }

            @Override
            public boolean matchesSafely(TextView textView) {
                if (null == this.expectedText) {
                    try {
                        this.expectedText = textView.getResources().getString(
                                resourceId);
                        this.resourceName = textView.getResources()
                                .getResourceEntryName(resourceId);
                    } catch (Resources.NotFoundException ignored) {
                    /*
                     * view could be from a context unaware of the resource
                     * id.
                     */
                    }
                }
                if (null != this.expectedText) {
                    return this.expectedText.equals(textView.getText()
                            .toString());
                } else {
                    return false;
                }
            }
        };
    }
}