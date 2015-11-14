package test.coding.fuse.presentation.presenter;

import android.text.Editable;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Named;

import test.coding.fuse.domain.interactor.DefaultSubscriber;
import test.coding.fuse.domain.interactor.Interactor;
import test.coding.fuse.domain.interactor.SearchCompanyInteractor;
import test.coding.fuse.domain.model.Company;
import test.coding.fuse.presentation.dagger.PerActivity;
import test.coding.fuse.presentation.mapper.CompanyModelMapper;
import test.coding.fuse.presentation.model.CompanyModel;
import test.coding.fuse.presentation.view.CompanyView;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class CompanyPresenter implements Presenter {

    private static final String TAG = CompanyPresenter.class.getSimpleName();

    private CompanyView view;

    private final Interactor getCompanyInteractor;
    private final CompanyModelMapper companyModelMapper;

    @Inject
    public CompanyPresenter(@Named("searchCompany") Interactor getCompanyInteractor,
                            CompanyModelMapper companyModelMapper) {
        this.getCompanyInteractor = getCompanyInteractor;
        this.companyModelMapper = companyModelMapper;
    }

    public void setView(CompanyView view) {
        this.view = view;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.getCompanyInteractor.unsubscribe();
    }

    public void showError() {

        this.view.onSearchError();
    }

    public void showCompany(CompanyModel company) {
        this.view.onSearchSuccess(company);
    }

    public void searchCompany(String companyName) {
        ((SearchCompanyInteractor) getCompanyInteractor).setCompany(new Company(companyName, null));
        getCompanyInteractor.execute(new CompanySubscriber());
    }

    private final class CompanySubscriber extends DefaultSubscriber<Company> {

        @Override
        public void onCompleted() {
            Log.d(TAG, "CompanySubscriber | onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "CompanySubscriber | onError | Exception: " + e.getMessage(), e);
            CompanyPresenter.this.showError();
        }

        @Override
        public void onNext(Company company) {
            Log.d(TAG, "CompanySubscriber | onNext | Company: " + company.getName());
            CompanyPresenter.this.showCompany(companyModelMapper.parse(company));
        }
    }
}