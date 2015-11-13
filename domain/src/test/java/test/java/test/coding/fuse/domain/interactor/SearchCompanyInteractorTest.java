package test.java.test.coding.fuse.domain.interactor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.Executor;

import rx.Observable;
import rx.Scheduler;
import test.coding.fuse.domain.interactor.SearchCompanyInteractor;
import test.coding.fuse.domain.model.Company;
import test.coding.fuse.domain.repository.CompanyRepository;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SearchCompanyInteractorTest {

    SearchCompanyInteractor interactor;

    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private Company company;
    @Mock
    Executor executionThread;
    @Mock
    Scheduler uiThread;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        interactor = new SearchCompanyInteractor(companyRepository, executionThread, uiThread);
        interactor.setCompany(company);
    }

    @Test
    public void setComany() {
        assertTrue(company != null);
        assertTrue(company instanceof Company);
    }

    @Test
    public void buildObservable() {
        when(company.validate()).thenReturn(false);
        Observable observable = interactor.buildInteractorObservable();

        verify(company).validate();
        verify(observable,times(1)).error(any(Throwable.class));

        when(company.validate()).thenReturn(true);
        observable = interactor.buildInteractorObservable();
        verify(observable, never()).error(any(Throwable.class));
    }
}