package test.coding.fuse.presentation.view.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
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

    public static final String STATE_MODEL_COMPANY = "state_model_company";
    @Bind(R.id.et_company_name)
    AppCompatEditText etCompanyView;

    @Bind(R.id.im_company_logo)
    ImageView imCompanyLogo;

    @Inject
    CompanyPresenter presenter;
    private InteractorComponent interactorComponent;

    private boolean backgroundChanged;

    private CompanyModel company;

    @OnEditorAction(R.id.et_company_name)
    boolean onEditorAction(TextView view, int actionId, KeyEvent key) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            searchCompany();
        }
        return true;
    }

    @OnTextChanged(R.id.et_company_name)
    void onTextChanged(CharSequence text) {
        if (backgroundChanged)
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
    public void searchCompany() {
        backgroundChanged = true;
        presenter.searchCompany(etCompanyView.getText().toString());
    }

    @Override
    public void onSearchError() {
        changeEditTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
    }

    @Override
    public void onSearchSuccess(CompanyModel companyModel) {
        if(companyModel != null) {
            setCompanyModel(companyModel);
            changeEditTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark));
            showLogo();
        }
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
        backgroundChanged = false;
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
    }

    private void initializeComponent() {
        interactorComponent = DaggerInteractorComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        interactorComponent.inject(this);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if(savedInstanceState.containsKey(STATE_MODEL_COMPANY)) {
            onSearchSuccess((CompanyModel) savedInstanceState.getParcelable(STATE_MODEL_COMPANY));
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(STATE_MODEL_COMPANY, this.company);
        super.onSaveInstanceState(outState);
    }
}
