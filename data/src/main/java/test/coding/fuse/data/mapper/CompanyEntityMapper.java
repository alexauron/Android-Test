package test.coding.fuse.data.mapper;

import javax.inject.Inject;
import javax.inject.Singleton;

import test.coding.fuse.data.entity.CompanyEntity;
import test.coding.fuse.domain.model.Company;

@Singleton
public class CompanyEntityMapper {
    @Inject
    public CompanyEntityMapper() {}


    public Company parse(CompanyEntity companyEntity) {
        Company company = null;
        if (companyEntity != null) {
            company = new Company(companyEntity.getName(), companyEntity.getLogo());
        }

        return company;
    }
}
