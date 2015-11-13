package test.coding.fuse.presentation.view.activity;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.content.ContextCompat;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.inputmethod.EditorInfo;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import test.coding.fuse.presentation.R;
import test.coding.fuse.presentation.helper.EspressoHelper;
import test.coding.fuse.presentation.model.CompanyModel;

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
    public void companyLogoIsHideOnLoad() {
        onView(withId(R.id.im_company_logo)).check(matches(not(isDisplayed())));
    }

    @UiThreadTest
    public void onErrorTextIsRed() {
        mActivityRule.getActivity().onSearchError();
        onView(withId(R.id.et_company_name)).check(matches(
                EspressoHelper.withCurrentTextColor(ContextCompat.getColor(
                        mActivityRule.getActivity(), android.R.color.holo_red_dark))));
    }

    @UiThreadTest
    public void onSucceedTextIsGreen() {
        CompanyModel model = new CompanyModel();
        model.setName("mock");
        model.setLogo("mock");
        mActivityRule.getActivity().onSearchSuccess(model);
        onView(withId(R.id.et_company_name)).check(matches(
                EspressoHelper.withCurrentTextColor(ContextCompat.getColor(
                        mActivityRule.getActivity(), android.R.color.holo_green_dark))));
    }

    @UiThreadTest
    public void resetSetDefaultTextColor() {
        onView(withId(R.id.et_company_name)).check(matches(
                EspressoHelper.withCurrentTextColor(ContextCompat.getColor(
                        mActivityRule.getActivity(), android.R.color.primary_text_light))));
    }

    @UiThreadTest
    public void suceedShowImage() {
        CompanyModel model = new CompanyModel();
        model.setName("mock");
        model.setLogo("mock");
        mActivityRule.getActivity().onSearchSuccess(model);
        onView(withId(R.id.im_company_logo)).check(matches(isDisplayed()));
    }

}
