package test.coding.fuse.domain.interactor;


import java.util.concurrent.Executor;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;
import test.coding.fuse.domain.repository.CompanyRepository;

public class SearchCompanyInteractor extends Interactor {

    private final CompanyRepository companyRepository;
    private String companyName;

    @Inject
    public SearchCompanyInteractor(CompanyRepository companyRepository, Executor executionThread,
                                   Scheduler uiThread) {
        super(executionThread, uiThread);
        this.companyRepository = companyRepository;
    }

    @Override
    protected Observable buildInteractorObservable() {
        return this.companyRepository.searchCompany(companyName);
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}