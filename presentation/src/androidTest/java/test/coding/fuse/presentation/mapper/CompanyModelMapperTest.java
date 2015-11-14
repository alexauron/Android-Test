package test.coding.fuse.presentation.mapper;

import android.test.AndroidTestCase;

import test.coding.fuse.domain.model.Company;
import test.coding.fuse.presentation.model.CompanyModel;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CompanyModelMapperTest extends AndroidTestCase {

    public static final String MOCK_NAME = "mock";
    public static final String MOCK_LOGO = "mock";
    CompanyModelMapper mapper;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mapper = new CompanyModelMapper();
    }

    public void testParse() {
        Company company = new Company(MOCK_NAME, MOCK_LOGO);
        CompanyModel model = mapper.parse(company);

        assertThat(model, is(instanceOf(CompanyModel.class)));
        assertThat(model.getName(), is(MOCK_NAME));
        assertThat(model.getLogo(), is(MOCK_LOGO));
    }
}