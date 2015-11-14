package test.coding.fuse.domain.interactor;


import java.util.concurrent.Executor;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;
import test.coding.fuse.domain.exception.ModelDataValidationException;
import test.coding.fuse.domain.model.Company;
import test.coding.fuse.domain.repository.CompanyRepository;

public class SearchCompanyInteractor extends Interactor {

    private final CompanyRepository companyRepository;
    private Company company;

    @Inject
    public SearchCompanyInteractor(CompanyRepository companyRepository, Executor executionThread,
                                   Scheduler uiThread) {
        super(executionThread, uiThread);
        this.companyRepository = companyRepository;
    }

    @Override
    public Observable buildInteractorObservable() {
        Observable observable;
        if(company.validate()) {
            observable = this.companyRepository.searchCompany(company.getName());
        } else {
            observable = Observable.error(new ModelDataValidationException("Company name not valid"));
        }
        return observable;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}