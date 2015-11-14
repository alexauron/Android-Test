package test.coding.fuse.data.repository;

import android.test.AndroidTestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import test.coding.fuse.data.entity.CompanyEntity;
import test.coding.fuse.data.mapper.CompanyEntityMapper;
import test.coding.fuse.data.network.RestAPI;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CompanyDataRepositoryTest extends AndroidTestCase {

    public static final String COMPANY_NAME = "mock";
    CompanyDataRepository repository;

    @Mock
    private RestAPI restAPI;

    @Mock
    private CompanyEntityMapper companyEntityMapper;

    @Before
    public void setup() {
        repository = new CompanyDataRepository(companyEntityMapper, restAPI);
    }

    @Test
    public void searchCompany() {
        repository.searchCompany(COMPANY_NAME);

        verify(repository).searchCompany(COMPANY_NAME);
        verify(companyEntityMapper).parse(any(CompanyEntity.class));
    }
}