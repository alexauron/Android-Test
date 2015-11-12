package test.coding.fuse.presentation.view.activity;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.inputmethod.EditorInfo;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import test.coding.fuse.presentation.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasImeAction;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CompanyActivityTest {

    @Rule
    public ActivityTestRule<CompanyActivity> mActivityRule = new ActivityTestRule(CompanyActivity.class);

    @Test
    public void isShowingCompanyNameTextField() {
        onView(withId(R.id.et_company_name)).check(matches(isDisplayed()));
    }

    @Test
    public void companyNameTextFieldHasActionDone() {
        onView(withId(R.id.et_company_name)).check(matches(hasImeAction(EditorInfo.IME_ACTION_DONE)));
    }

    @Test
    public void companyLogoIsHide() {
        onView(withId(R.id.im_company_logo)).check(matches(not(isDisplayed())));
    }

}
