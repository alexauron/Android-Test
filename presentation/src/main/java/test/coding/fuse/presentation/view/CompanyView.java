package test.coding.fuse.presentation.view;

import test.coding.fuse.domain.model.Company;
import test.coding.fuse.presentation.model.CompanyModel;


public interface CompanyView {

    void searchCompany(String s);
    void onSearchError();
    void onSearchSuccess(CompanyModel companyModel);
    void searchReset();
}
