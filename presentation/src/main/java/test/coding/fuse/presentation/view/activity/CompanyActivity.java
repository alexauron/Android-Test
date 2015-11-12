package test.coding.fuse.presentation.view.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnEditorAction;
import butterknife.OnTextChanged;
import test.coding.fuse.domain.model.Company;
import test.coding.fuse.presentation.R;
import test.coding.fuse.presentation.dagger.entity.InteractorComponent;
import test.coding.fuse.presentation.dagger.entity.DaggerInteractorComponent;
import test.coding.fuse.presentation.presenter.CompanyPresenter;
import test.coding.fuse.presentation.view.CompanyView;

public class CompanyActivity extends BaseActivity implements CompanyView {

    @Bind(R.id.et_company_name)
    AppCompatEditText etCompanyView;

    @Bind(R.id.im_company_logo)
    ImageView imCompanyLogo;

    @Inject
    CompanyPresenter presenter;
    private InteractorComponent interactorComponent;

    private boolean backgroundChanged;

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
        etCompanyView.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
    }

    @Override
    public void onSearchSuccess(Company company) {
        etCompanyView.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark));
        showLogo(company.getLogo());
    }

    private void showLogo(String logoURL) {
        imCompanyLogo.setVisibility(View.VISIBLE);
        Picasso.with(this).load(logoURL).into(imCompanyLogo);
    }

    @Override
    public void searchReset() {
        backgroundChanged = false;
        imCompanyLogo.setVisibility(View.GONE);
        etCompanyView.setTextColor(ContextCompat.getColor(this, android.R.color.primary_text_light));
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
}
