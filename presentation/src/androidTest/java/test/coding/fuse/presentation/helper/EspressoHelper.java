package test.coding.fuse.presentation.helper;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static org.hamcrest.Matchers.is;

/**
 * Created by alexauron on 12/11/2015.
 */
public class EspressoHelper {
    /**
     * Returns a matcher that matches {@link TextView}s based on text property value. Note: View's
     * text property is never null. If you setText(null) it will still be "". Do not use null
     * matcher.
     *
     * @param integerMatcher {@link Matcher} of {@link String} with text to match
     */
    public static Matcher<View> withCurrentTextColor(final Matcher<Integer> integerMatcher) {
        return new BoundedMatcher<View, TextView>(TextView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("with text color: ");
                integerMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(TextView textView) {
                return integerMatcher.matches(textView.getCurrentTextColor());
            }
        };
    }

    /**
     * Returns a matcher that matches {@link TextView} based on it's text property value. Note:
     * View's Sugar for withTextColor(is("string")).
     */
    public static Matcher<View> withCurrentTextColor(int color) {
        return withCurrentTextColor(is(color));
    }
}
