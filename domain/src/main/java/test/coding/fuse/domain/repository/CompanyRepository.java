package test.coding.fuse.domain.repository;

import rx.Observable;
import test.coding.fuse.domain.model.Company;


public interface CompanyRepository {

    Observable<Company> searchCompany(String companyName);
}