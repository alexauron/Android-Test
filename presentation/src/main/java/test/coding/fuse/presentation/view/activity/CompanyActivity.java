package test.coding.fuse.presentation.view.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnEditorAction;
import butterknife.OnTextChanged;
import test.coding.fuse.presentation.R;
import test.coding.fuse.presentation.dagger.entity.DaggerInteractorComponent;
import test.coding.fuse.presentation.dagger.entity.InteractorComponent;
import test.coding.fuse.presentation.model.CompanyModel;
import test.coding.fuse.presentation.presenter.CompanyPresenter;
import test.coding.fuse.presentation.view.CompanyView;

public class CompanyActivity extends BaseActivity implements CompanyView {

    private static final String STATE_MODEL_COMPANY = "state_model_company";
    private static final String STATE_TEXT_LAST_SEARCH = "state_text_last_search";

    @Bind(R.id.et_company_name)
    AppCompatEditText etCompanyView;

    @Bind(R.id.im_company_logo)
    ImageView imCompanyLogo;

    @Inject
    CompanyPresenter presenter;
    private InteractorComponent interactorComponent;

    private CompanyModel company;

    private String lastSearch;

    @OnEditorAction(R.id.et_company_name)
    boolean onEditorAction(TextView view, int actionId, KeyEvent key) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            searchCompany(etCompanyView.getText().toString());
        }
        return true;
    }

    @OnTextChanged(R.id.et_company_name)
    void onTextChanged(CharSequence text) {
        if ((!TextUtils.isEmpty(lastSearch) && !TextUtils.equals(text, this.lastSearch)))
            searchReset();

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public void searchCompany(String companyName) {
        lastSearch = companyName;
        presenter.searchCompany(companyName);
    }

    @Override
    public void onSearchError() {
        setCompanyModel(new CompanyModel(etCompanyView.getText().toString(), null));
        changeEditTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
    }

    @Override
    public void onSearchSuccess(CompanyModel companyModel) {
        setCompanyModel(companyModel);
        changeEditTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark));
        showLogo();
    }

    private void changeEditTextColor(int color) {
        etCompanyView.setTextColor(color);
    }

    private void setCompanyModel(CompanyModel companyModel) {
        this.company = companyModel;
    }

    private void showLogo() {
        imCompanyLogo.setVisibility(View.VISIBLE);
        Picasso.with(this).load(this.company.getLogo()).into(imCompanyLogo);
    }

    @Override
    public void searchReset() {
        setCompanyModel(null);
        imCompanyLogo.setVisibility(View.GONE);
        changeEditTextColor(ContextCompat.getColor(this, android.R.color.primary_text_light));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_company;
    }

    @Override
    protected void onCreateExtended(Bundle savedInstanceState) {
        initializeComponent();
        this.presenter.setView(this);

        restoreState(savedInstanceState);
    }

    private void restoreState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if(savedInstanceState.containsKey(STATE_TEXT_LAST_SEARCH)) {
                lastSearch = savedInstanceState.getString(STATE_TEXT_LAST_SEARCH);
            }
            if (savedInstanceState.containsKey(STATE_MODEL_COMPANY)) {
                setCompanyModel((CompanyModel) savedInstanceState.getParcelable(STATE_MODEL_COMPANY));

                if(this.company.getLogo() != null) {
                    onSearchSuccess(this.company);
                } else {
                    onSearchError();
                }
            }
        }

    }

    private void initializeComponent() {
        interactorComponent = DaggerInteractorComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        interactorComponent.inject(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (this.company != null)
            outState.putParcelable(STATE_MODEL_COMPANY, this.company);
        if(this.lastSearch != null)
            outState.putString(STATE_TEXT_LAST_SEARCH, this.lastSearch);
        super.onSaveInstanceState(outState);
    }
}
