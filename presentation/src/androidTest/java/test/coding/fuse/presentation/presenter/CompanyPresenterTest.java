package test.coding.fuse.presentation.presenter;


import android.test.AndroidTestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import rx.Subscriber;
import test.coding.fuse.domain.interactor.SearchCompanyInteractor;
import test.coding.fuse.domain.model.Company;
import test.coding.fuse.presentation.mapper.CompanyModelMapper;
import test.coding.fuse.presentation.view.CompanyView;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CompanyPresenterTest extends AndroidTestCase {

    private CompanyPresenter presenter;

    @Mock
    private CompanyView view;

    @Mock
    private SearchCompanyInteractor getCompanyInteractor;

    @Mock
    private CompanyModelMapper companyModelMapper;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        MockitoAnnotations.initMocks(this);
        presenter = new CompanyPresenter(getCompanyInteractor, companyModelMapper);
        presenter.setView(view);
    }

    @Test
    public void showError() {
        presenter.showError();
        verify(view).onSearchError();
    }

    @Test
    public void sarchCompany() {
        presenter.searchCompany(any(String.class));

        verify(getCompanyInteractor).setCompany(any(Company.class));
        verify(getCompanyInteractor).execute(any(Subscriber.class));
    }
}
