package test.coding.fuse.data.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;
import test.coding.fuse.data.entity.CompanyEntity;
import test.coding.fuse.data.mapper.CompanyEntityMapper;
import test.coding.fuse.data.network.RestAPI;
import test.coding.fuse.domain.model.Company;
import test.coding.fuse.domain.repository.CompanyRepository;


@Singleton
public class CompanyDataRepository implements CompanyRepository {

    private final RestAPI restAPI;

    private final CompanyEntityMapper companyEntityMapper;


    @Inject
    public CompanyDataRepository(CompanyEntityMapper companyEntityMapper, RestAPI restAPI) {
        this.companyEntityMapper = companyEntityMapper;
        this.restAPI = restAPI;
    }

    @Override
    public Observable<Company> searchCompany(String companyName) {
        return restAPI.companySearch(companyName).map(new Func1<CompanyEntity, Company>() {
            @Override
            public Company call(CompanyEntity companyEntity) {
                return CompanyDataRepository.this.companyEntityMapper.parse(companyEntity);
            }
        });
    }
}