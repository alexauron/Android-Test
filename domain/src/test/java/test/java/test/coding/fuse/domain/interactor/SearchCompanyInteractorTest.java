package test.java.test.coding.fuse.domain.interactor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.Executor;

import rx.Scheduler;
import test.coding.fuse.domain.interactor.SearchCompanyInteractor;
import test.coding.fuse.domain.model.Company;
import test.coding.fuse.domain.repository.CompanyRepository;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
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
    public void buildObservableErrorValidation() {
        when(company.validate()).thenReturn(true);
        interactor.buildInteractorObservable();

        verify(companyRepository).searchCompany(company.getName());
    }

    @Test
    public void buildObservableValidationOK() {
        when(company.validate()).thenReturn(false);
        interactor.buildInteractorObservable();

        verifyZeroInteractions(companyRepository);
    }
}