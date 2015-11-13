package test.coding.fuse.presentation.mapper;

import javax.inject.Inject;
import javax.inject.Singleton;

import test.coding.fuse.domain.model.Company;
import test.coding.fuse.presentation.dagger.PerActivity;
import test.coding.fuse.presentation.model.CompanyModel;

@PerActivity
public class CompanyModelMapper {

    @Inject
    public CompanyModelMapper() {
    }


    public CompanyModel parse(Company company) {
        CompanyModel companyModel = null;
        if (company != null) {
            companyModel = new CompanyModel(company.getName(), company.getLogo());
        }

        return companyModel;
    }
}
