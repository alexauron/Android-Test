package test.coding.fuse.presentation.view;

import test.coding.fuse.domain.model.Company;


public interface CompanyView {

    void searchCompany();
    void onSearchError();
    void onSearchSuccess(Company company);
    void searchReset();
}
