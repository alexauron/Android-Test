package test.java.test.coding.fuse.domain.model;


import org.junit.Before;
import org.junit.Test;

import test.coding.fuse.domain.model.Company;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CompanyTest {

    private static final String MOCK_NAME = "name";
    private static final String MOCK_LOGO = "logo";

    private Company company;

    @Before
    public void setup() {
        this.company = new Company(MOCK_NAME, MOCK_LOGO);
    }

    @Test
    public void constructor() {
        assertThat(this.company.getName(), is(MOCK_NAME));
        assertThat(this.company.getLogo(), is(MOCK_LOGO));
    }

    @Test
    public void validate() {
        assertThat(this.company.validate(), is(true));

        Company empty = new Company("", "");
        assertThat(empty.validate(), is(false));
    }
}
