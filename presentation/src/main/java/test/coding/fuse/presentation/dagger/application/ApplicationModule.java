package test.coding.fuse.presentation.dagger.application;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import test.coding.fuse.data.network.RestAPI;
import test.coding.fuse.data.network.RestApiImpl;
import test.coding.fuse.data.repository.CompanyDataRepository;
import test.coding.fuse.domain.executor.JobExecutor;
import test.coding.fuse.domain.executor.ThreadExecutor;
import test.coding.fuse.domain.repository.CompanyRepository;
import test.coding.fuse.presentation.AndroidApplication;

/**
 * Provide object for application scope
 */
@Module
public class ApplicationModule {
    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }


    @Provides
    @Singleton
    ThreadExecutor provideExecutionThread(JobExecutor executor) {
        return executor;
    }

    @Provides
    @Singleton
    Scheduler provideUIThread() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    RestAPI provideRestAPI() {
        return new RestApiImpl();
    }

    @Provides
    @Singleton
    CompanyRepository provideCompanyRepository(CompanyDataRepository companyRepository) {
        return companyRepository;
    }
}