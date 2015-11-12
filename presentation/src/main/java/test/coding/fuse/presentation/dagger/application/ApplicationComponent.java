package test.coding.fuse.presentation.dagger.application;


import android.content.Context;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Component;
import rx.Scheduler;
import test.coding.fuse.data.network.RestAPI;
import test.coding.fuse.domain.executor.ThreadExecutor;
import test.coding.fuse.domain.repository.CompanyRepository;
import test.coding.fuse.presentation.view.activity.BaseActivity;

/**
 * Application scope component
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    Context context();


    ThreadExecutor executionThread();
    Scheduler uiThread();
    RestAPI restAPI();
    CompanyRepository companyRepository();
}
