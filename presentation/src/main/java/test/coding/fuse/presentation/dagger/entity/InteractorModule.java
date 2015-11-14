package test.coding.fuse.presentation.dagger.entity;

import java.util.concurrent.Executor;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import test.coding.fuse.domain.executor.ThreadExecutor;
import test.coding.fuse.domain.interactor.Interactor;
import test.coding.fuse.domain.interactor.SearchCompanyInteractor;
import test.coding.fuse.domain.repository.CompanyRepository;
import test.coding.fuse.presentation.dagger.PerActivity;
import test.coding.fuse.presentation.presenter.CompanyPresenter;
import test.coding.fuse.presentation.presenter.Presenter;

/**
 * Dagger module that provides user related collaborators.
 */
@Module
public class InteractorModule {

    public InteractorModule() {
    }


    @Provides
    @PerActivity
    @Named("searchCompany")
    Interactor provideGetCompanyInteractor(
            CompanyRepository companyRepository, ThreadExecutor executionThread,
            Scheduler uiThread) {
        return new SearchCompanyInteractor(companyRepository, executionThread, uiThread);
    }

}