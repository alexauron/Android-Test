package test.coding.fuse.data.mapper;


import android.test.AndroidTestCase;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import test.coding.fuse.data.entity.CompanyEntity;
import test.coding.fuse.domain.model.Company;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CompanyEntityMapperTest extends AndroidTestCase {

    CompanyEntityMapper mapper;

    private static final String MOCK_NAME = "name";
    private static final String MOCK_LOGO = "logo";

    private CompanyEntity companyEntity;

    @Before
    public void setup() {
        mapper = new CompanyEntityMapper();
        companyEntity = new CompanyEntity();
        companyEntity.setName(MOCK_NAME);
        companyEntity.setLogo(MOCK_LOGO);
    }

    @Test
    public void parse() {
        Company company = mapper.parse(companyEntity);

        assertThat(company.getName(), is(companyEntity.getName()));
        assertThat(company.getLogo(), is(companyEntity.getLogo()));
    }
}
